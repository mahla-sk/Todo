package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_login.setOnClickListener {
            if (et_user.text.isNullOrEmpty() || et_pass.text.isNullOrEmpty()) {
                Toast.makeText(this, "please fill the fields", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this, "welcome", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainTodoActivity::class.java))
            }
        }
    }
}