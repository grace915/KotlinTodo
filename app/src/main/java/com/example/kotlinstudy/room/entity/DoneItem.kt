package com.example.kotlinstudy.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "done")
data class DoneItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    var sDate: String,
    var dDate: String,
    var memo: String,
    var doneDate: String,
    var checked: Boolean = false
)