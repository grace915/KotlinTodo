package com.example.kotlinstudy.room.dao

import androidx.room.*
import com.example.kotlinstudy.room.entity.DoneItem
import com.example.kotlinstudy.room.entity.TodoItem

@Dao
interface DoneDao {
//필요한거 : 삭제, 전체 삭제, 들고오기 필요한가?, 일단 다해보자, 아 여기에는 그 시간이 중요한데 체크되었을 때 시간을 알아야 하잖아.

    @Insert
    fun insertTodo(item : DoneItem)

    @Delete
    fun deleteTodo(item: DoneItem)

    @Update
    fun updateTodo(item: DoneItem)

    @Query("SELECT * FROM todo ORDER BY eDate")
    //eDate가 아니라 저장했을 때 데이터를 불러와야할 듯? 그건 따로 만들자 가 아니라 doneDate 씁시다
    fun getAllTodo(): List<DoneItem>

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getTodo(id: Int): DoneItem

}
