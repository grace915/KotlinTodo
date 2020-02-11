package com.example.kotlinstudy.room.entity


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kotlinstudy.room.entity.TodoItem
import kotlinx.android.parcel.Parcelize

@Parcelize

@Entity(tableName = "todo")
data class TodoItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    var sDate: String,
    var eDate: String,
    var memo: String
) : Parcelable {
    var checked: Boolean = false
}
