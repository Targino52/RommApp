package com.example.listapi.repository

import com.example.listapi.room.TodoDB
import com.example.listapi.room.TodoEntity

class Repository(val todosDB: TodoDB) {

    suspend fun addTodoToRomm(todoEntity: TodoEntity) {
        todosDB.todoDao().addTodo(todoEntity)
    }

    fun getAllTodos() = todosDB.todoDao().getAllTodos()

    suspend fun deleteTodoFromRoom(todoEntity: TodoEntity) {
        todosDB.todoDao().deleteTodo(todoEntity)
    }

}