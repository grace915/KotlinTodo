package com.example.kotlinstudy.room.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kotlinstudy.room.entity.TodoItem

@Entity(tableName = "todo")
data class TodoItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    var sDate: String,
    var eDate: String,
    var memo: String
) {
    var checked: Boolean = false
}
