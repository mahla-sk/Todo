package com.example.todoapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main_todo.*

class MainTodoActivity : AppCompatActivity(),TodoAdapter.OnTodoItemClickedListener {

    private var todoDatabase: Tododb? = null
    private var todoAdapter: TodoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_todo)

        todoDatabase = Tododb.getInstance(this)
        todoAdapter = TodoAdapter()
        todoAdapter?.setTodoItemClickedListener(this)

        add_btn.setOnClickListener { startActivity(Intent(this, AddTodoActivity::class.java)) }
    }
    override fun onResume() {
        super.onResume()
        todoAdapter?.todoList=todoDatabase?.getTodoDao()?.getTodoList()
        todo_rv.adapter = todoAdapter
        todo_rv.layoutManager = LinearLayoutManager(this)
        todo_rv.hasFixedSize()
    }

    override fun onTodoItemClicked(todo: Todo) {
        val intent = Intent(this, AddTodoActivity::class.java)
        intent.putExtra("id", todo.id)
        intent.putExtra("title", todo.title)
        intent.putExtra("checked", todo.checking)
        intent.putExtra("date", todo.date)
        startActivity(intent)
    }

    override fun onTodoItemLongClicked(todo: Todo) {
        val alertDialog = AlertDialog.Builder(this)
            .setItems(R.array.dialog_list, DialogInterface.OnClickListener { dialog, which ->
                if (which==0){
                    val intent = Intent(this@MainTodoActivity, AddTodoActivity::class.java)
                    intent.putExtra("id", todo.id)
                    intent.putExtra("title", todo.title)
                    intent.putExtra("checked", todo.checking)
                    intent.putExtra("date", todo.date)
                    startActivity(intent)
                }else{
                    todoDatabase?.getTodoDao()?.removeTodo(todo)
                    onResume()
                }
                dialog.dismiss()
            })
            .create()
        alertDialog.show()
    }
}
