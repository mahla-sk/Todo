package com.example.todoapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Todo::class], version = 1, exportSchema = false)
    abstract class Tododb: RoomDatabase(){

        abstract fun getTodoDao(): TodoDao

        companion object {
            val databaseName = "tododb"
            var todoDatabase: Tododb? = null

            fun getInstance(context: Context): Tododb?{
                if (todoDatabase == null){
                    todoDatabase = Room.databaseBuilder(context,
                        Tododb::class.java,
                        Tododb.databaseName)
                        .allowMainThreadQueries()
                        .build()
                }
                return todoDatabase
            }
        }
    }
