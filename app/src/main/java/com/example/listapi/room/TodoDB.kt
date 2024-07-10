package com.example.listapi.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [TodoEntity::class], version = 1, exportSchema = false)
abstract class TodoDB : RoomDatabase() {

    abstract fun todoDao(): TodoDAO

    companion object {

        @Volatile
    private var INSTANCE: TodoDB? = null

        fun getInstance(context: Context): TodoDB {

            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TodoDB::class.java,
                        "todos_db"
                    ).build()

                }
                return instance
            }
        }
    }
}