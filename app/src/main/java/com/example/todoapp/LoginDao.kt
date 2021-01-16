package com.example.todoapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LoginDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun savelogin(login:Login)
    @Query("SELECT * FROM login WHERE username LIKE  :uname")
    fun search(uname: String?): Login
    @Query("SELECT * FROM login WHERE password LIKE  :pass")
    fun searchpass(pass: String?): Login
}