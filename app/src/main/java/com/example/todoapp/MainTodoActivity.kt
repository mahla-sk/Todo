package com.example.todoapp

import android.content.DialogInterface
import android.content.Intent
import android.icu.text.TimeZoneFormat
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.IntegerRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_add_todo.*
import kotlinx.android.synthetic.main.activity_add_todo.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_todo.*
import kotlinx.android.synthetic.main.activity_main_todo.add_btn
import kotlinx.android.synthetic.main.empty_lay.view.*
import kotlinx.android.synthetic.main.todo_item_view.view.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.reflect.typeOf

class MainTodoActivity : AppCompatActivity(), TodoAdapter.OnTodoItemClickedListener {

    private var todoDatabase: Tododb? = null
    private var todoAdapter: TodoAdapter? = null
    private var loginDatabase: logindb? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_todo)

        todoDatabase = Tododb.getInstance(this)
        todoAdapter = TodoAdapter()
        todoAdapter?.setTodoItemClickedListener(this)
        loginDatabase = logindb.getInstance(this)

        add_btn.setOnClickListener { startActivity(Intent(this, AddTodoActivity::class.java)) }

        // val ddate = LocalDate.now().format(DateTimeFormatter.ofPattern("M/d/y"))
        // val ttime = LocalTime.now().format(DateTimeFormatter.ofPattern("H:m"))

        val dateTime = LocalDateTime.now()
        val date = dateTime.format(DateTimeFormatter.ofPattern("Mdy"))
        Toast.makeText(this, "its " + date, Toast.LENGTH_LONG).show()


        srchbtn.setOnClickListener {
            val key = srch.text.toString()
            if (todoDatabase!!.getTodoDao().searching(key) != null) {
                var l = todoDatabase!!.getTodoDao().idSearch(key)
                Toast.makeText(this, "found with this id: " + l.toString(), Toast.LENGTH_LONG)
                    .show()
                todo_rv.smoothScrollToPosition(l)
            } else Toast.makeText(this, "not found!", Toast.LENGTH_SHORT).show()

        }

        if (todoDatabase!!.getTodoDao().datesearch(date) != null) {
            val d = todoDatabase!!.getTodoDao().dateidSearch(date)
            var todo = todoDatabase!!.getTodoDao().getTodoItem(d)
            if (date == todo.date)
                todo.title="expired"
            todoDatabase!!.getTodoDao().updateTodo(todo)
        }

//            if (todoDatabase!!.getTodoDao().datesearching() != null) {
//
//                if ((date.toInt() - todoDatabase!!.getTodoDao().datesearching().toInt()) > 0)
//                    Toast.makeText(this, "good", Toast.LENGTH_LONG).show()
//
//
//            }

    }

    override fun onResume() {
        super.onResume()
        todoAdapter?.todoList = todoDatabase?.getTodoDao()?.getTodoList()
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
                if (which == 0) {
                    val intent = Intent(this@MainTodoActivity, AddTodoActivity::class.java)
                    intent.putExtra("id", todo.id)
                    intent.putExtra("title", todo.title)
                    intent.putExtra("checked", todo.checking)
                    intent.putExtra("date", todo.date)
                    startActivity(intent)
                } else {
                    todoDatabase?.getTodoDao()?.removeTodo(todo)
                    onResume()
                }
                dialog.dismiss()
            })
            .create()
        alertDialog.show()
    }


}
