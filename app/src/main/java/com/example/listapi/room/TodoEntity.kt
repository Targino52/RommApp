package com.example.listapi.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String
)
