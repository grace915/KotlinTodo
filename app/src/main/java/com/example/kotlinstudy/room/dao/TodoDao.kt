package com.example.kotlinstudy.room.dao

import androidx.room.*
import com.example.kotlinstudy.room.entity.TodoItem


@Dao
interface TodoDao {
    @Insert
    fun insertTodo(item: TodoItem)

    @Delete
    fun deleteTodo(item: TodoItem)

    @Update
    fun updateTodo(item: TodoItem)

    @Query("SELECT * FROM todo ORDER BY dDate")
    fun getTodos(): List<TodoItem>

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getTodo(id: Int): TodoItem

    @Query("SELECT * FROM todo WhERE checked == 1")
    fun getTodoDone(): List<TodoItem>
}