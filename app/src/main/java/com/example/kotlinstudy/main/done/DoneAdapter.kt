package com.example.kotlinstudy.main.done

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinstudy.R
import com.example.kotlinstudy.main.todo.TodoViewHolder
import com.example.kotlinstudy.room.database.MyDatabase
import com.example.kotlinstudy.room.entity.DoneItem
import com.example.kotlinstudy.room.entity.TodoItem


class DoneAdapter(private val context: Context) : RecyclerView.Adapter<DoneViewHolder>() {

    private val myDatabase: MyDatabase? = MyDatabase.getInstance(context)
    var itemList: MutableList<DoneItem> = mutableListOf()

    init {
        val items = myDatabase?.doneDao()?.getAllTodo()?.also {
            itemList.addAll(it)
        }
        notifyDataSetChanged()
    }

    fun deleteItem(item: DoneItem) {

        myDatabase?.doneDao()?.deleteTodo(item)

        itemList.remove(item)
        notifyDataSetChanged()
    }

    fun refresh() {

        myDatabase?.doneDao()?.getAllTodo()?.also {
            itemList.clear()
            itemList.addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoneViewHolder {
        var viewHolder = DoneViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_collection,
                parent,
                false
            )
        )

        viewHolder.itemView.setOnClickListener{

        }

        return viewHolder


    }
    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: DoneViewHolder, position: Int) {
        holder.onbind(itemList[position])
    }

}