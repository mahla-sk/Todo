package com.example.todoapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Login::class], version = 1)
abstract class logindb : RoomDatabase() {
    abstract fun getLoginDao(): LoginDao

    companion object {
        val databaseName = "logindb"
        var loginDatabase: logindb? = null

        fun getInstance(context: Context): logindb?{
            if (loginDatabase == null){
                loginDatabase = Room.databaseBuilder(context,
                    logindb::class.java,
                    logindb.databaseName)
                    .allowMainThreadQueries()
                    .build()
            }
            return loginDatabase
        }
}
}