package com.example.kotlinstudy.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DoneItem")
data class DoneItem(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    var name: String,
    var sDate: String,
    var eDate: String,
    var memo: String
   //var doneDate: String
)