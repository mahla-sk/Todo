package com.example.todoapp

import androidx.room.*
import java.util.*

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

    @Query("SELECT * FROM todo WHERE todo_title LIKE '%' || :search || '%'")
    fun searching(search: String?): Todo

    @Query("SELECT id FROM todo WHERE todo_title LIKE '%' || :srch || '%'")
    fun idSearch(srch: String): Int

    @Query("SELECT id FROM todo WHERE todo_date LIKE :date")
    fun dateidSearch(date: String): Int

    @Query("SELECT * FROM todo WHERE todo_date LIKE :date")
    fun datesearch(date: String?): Todo

    @Query("SELECT todo_date FROM todo")
    fun datesearching(): String

}