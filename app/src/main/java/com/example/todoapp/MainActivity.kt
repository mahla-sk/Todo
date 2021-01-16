package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.todoapp.logindb.Companion.loginDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_todo.*

class MainActivity : AppCompatActivity() {
    private var loginDatabase: logindb? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginDatabase = logindb.getInstance(this)

        btn_login.setOnClickListener {
            if (et_user.text.isNullOrEmpty() || et_pass.text.isNullOrEmpty()) {
                Toast.makeText(this, "please fill the fields", Toast.LENGTH_SHORT)
                    .show()
            } else {

                if (loginDatabase!!.getLoginDao()
                        .search(et_user.text.toString()) != null && loginDatabase!!.getLoginDao()
                        .searchpass(et_pass.text.toString()) != null
                ) {
                    val intent = Intent(this, MainTodoActivity::class.java)
                    intent.putExtra("username", et_user.text.toString())
                    intent.putExtra("password", et_pass.text.toString())
                    startActivity(Intent(this, MainTodoActivity::class.java))
                    Toast.makeText(this, "welcome", Toast.LENGTH_SHORT).show()
                }
                else{Toast.makeText(this,"please register first",Toast.LENGTH_LONG).show()}
            }
        }
        btn_register.setOnClickListener {
            val user=et_user.text.toString()
            val pass=et_pass.text.toString()
            val login=Login(user,pass)
            loginDatabase!!.getLoginDao().savelogin(login)
            Toast.makeText(this,"register is done",Toast.LENGTH_LONG).show()
        }
    }
}