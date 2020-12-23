package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_add_todo.*
import kotlinx.android.synthetic.main.todo_item_view.*

class AddTodoActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {
    private var todoDatabase: Tododb? = null
    private var priority = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        todoDatabase = Tododb.getInstance(this)
        radioGroup.setOnCheckedChangeListener(this)

        val title = intent.getStringExtra("title")
        if (title == null || title == ""){
            add_btn.setOnClickListener{
                val todo = Todo(title_et.text.toString(),priority,0,date_et.text.toString())
                todo.date= date_et.text.toString()
                todoDatabase!!.getTodoDao().saveTodo(todo)
                finish()
            }
        }else{
            add_btn.text= getString(R.string.update)
            val tId = intent.getIntExtra("id", 0)
            title_et.setText(title)
            add_btn.setOnClickListener {
                val todo = Todo(title_et.text.toString(), priority,tId,date_et.text.toString())
                todo.date = date_et.text.toString()
                todoDatabase!!.getTodoDao().updateTodo(todo)
                finish()
            }
        }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        if (checkedId == R.id.done){
            priority = 1
        }else if (checkedId == R.id.undone) {
            priority = 2
        }
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home){
            startActivity(Intent(this, MainTodoActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}
