package com.example.listapi.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface TodoDAO {

    @Insert
    suspend fun addTodo(todoEntity: TodoEntity)

    @Query("SELECT * FROM TodoEntity")
    fun getAllTodos(): Flow<List<TodoEntity>>

    @Delete
    suspend fun deleteTodo(todoEntity: TodoEntity)

}