package com.example.kotlinstudy.main.done

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinstudy.R
import com.example.kotlinstudy.main.todo.TodoViewHolder
import com.example.kotlinstudy.room.database.MyDatabase
import com.example.kotlinstudy.room.entity.DoneItem
import com.example.kotlinstudy.room.entity.TodoItem
import kotlinx.android.synthetic.main.item_collection.view.*
import java.text.SimpleDateFormat
import java.util.*


class DoneAdapter(private val context: Context) : RecyclerView.Adapter<DoneViewHolder>()  {

    private val id : Int? = null
    private val myDatabase: MyDatabase? = MyDatabase.getInstance(context)
    var itemList: MutableList<DoneItem> = mutableListOf()

    init {
         myDatabase?.doneDao()?.getDones()?.also {
            itemList.addAll(it)
        }

        notifyDataSetChanged()
    }



    fun deleteItem(item : DoneItem){
        myDatabase?.doneDao()?.deleteDone(item)
        itemList.remove(item)
    }

    fun refresh() {

        myDatabase?.doneDao()?.getDones()?.also {
            itemList.clear()
            itemList.addAll(it)

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

        viewHolder.itemView.setOnClickListener {

            itemList[viewHolder.adapterPosition].also {

                it.checked = !it.checked
                myDatabase?.doneDao()?.updateDone(it)
                //myDatabase?.doneDao()?.getDone(id!!).also{


                //}

            }
            notifyDataSetChanged()
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