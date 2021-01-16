package com.example.todoapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "login")
class Login(
    @ColumnInfo(name = "username")
    var u_name:String = "",
    @ColumnInfo(name = "password")
    var pass: String="",
    @PrimaryKey(autoGenerate = true) var id: Int = 0)