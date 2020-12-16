package com.example.todoapp

import androidx.room.*

@Dao
interface TodoDao {
        @Query("SELECT*FROM todo ORDER BY id ASC")
        fun getTodoList(): List<Todo>
        @Query("SELECT*FROM todo WHERE id=:id")
        fun getTodoItem(id: Int): Todo
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        fun saveTodo(todo: Todo)

        @Update
        fun updateTodo(todo: Todo)

        @Delete
        fun removeTodo(todo: Todo)
    }