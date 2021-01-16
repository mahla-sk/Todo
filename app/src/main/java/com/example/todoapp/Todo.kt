package com.example.todoapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "todo")
class Todo(
    @ColumnInfo(name = "todo_title")
    var title:String = "",
    @ColumnInfo(name = "todo_checking")
    var checking: Int = 0,
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "todo_date")
    var date: String="")