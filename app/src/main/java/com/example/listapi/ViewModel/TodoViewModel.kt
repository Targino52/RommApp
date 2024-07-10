package com.example.listapi.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listapi.repository.Repository
import com.example.listapi.room.TodoEntity
import kotlinx.coroutines.launch

class TodoViewModel(val repository: Repository): ViewModel() {

    fun addTodo(todo: TodoEntity) {

        viewModelScope.launch {
            repository.addTodoToRomm(todo)
        }
    }

    val todos = repository.getAllTodos()

    fun deleteTodo(todo: TodoEntity) {
        viewModelScope.launch {
            repository.deleteTodoFromRoom(todo)
        }

    }
}