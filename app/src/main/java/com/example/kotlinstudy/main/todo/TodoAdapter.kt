package com.example.kotlinstudy.main.todo

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinstudy.R
import com.example.kotlinstudy.add_edit.AddEditTodoActivity
import com.example.kotlinstudy.room.database.MyDatabase
import com.example.kotlinstudy.room.entity.TodoItem

class TodoAdapter(private val context: Context) : RecyclerView.Adapter<TodoViewHolder>() {
    private val myDatabase: MyDatabase? = MyDatabase.getInstance(context)
    var itemList: MutableList<TodoItem> = mutableListOf()
    val doneMenu = R.menu.menu_checked

    init {

        val items = myDatabase?.todoDao()?.getTodos()?.also {
            itemList.addAll(it)
        }
        notifyDataSetChanged()


    }

    fun addItem(item: TodoItem) {
        itemList.add(item)
        myDatabase?.todoDao()?.insertTodo(item)
        notifyDataSetChanged()
    }

    fun deleteItem(item: TodoItem) {

        myDatabase?.todoDao()?.deleteTodo(item)

        itemList.remove(item)
        notifyDataSetChanged()
    }

    fun refresh() {

        myDatabase?.todoDao()?.getTodos()?.also {
            itemList.clear()
            itemList.addAll(it)
            notifyDataSetChanged()
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        var viewHolder = TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )

        viewHolder.itemView.setOnClickListener {

            itemList[viewHolder.adapterPosition].also {
                it.checked = !it.checked
                myDatabase?.todoDao()?.updateTodo(it)

            }

            notifyDataSetChanged()
            /*if(itemList[viewHolder.adapterPosition].checked){

            }*/
        }



        viewHolder.itemView.setOnLongClickListener {
            val builder = AlertDialog.Builder(parent.context)
            val menu: Array<String> = arrayOf("삭제", "수정", "취소")
            builder.setTitle(itemList[viewHolder.adapterPosition].name)
            builder.setItems(menu) { dialog, which ->
                when (menu[which]) {
                    "삭제" -> deleteItem(itemList[viewHolder.adapterPosition])
                    "수정" -> {

                        val editIntent = Intent(context, AddEditTodoActivity::class.java)
                        editIntent.putExtra("mode", AddEditTodoActivity.MODE_EDIT)
                        editIntent.putExtra("item_id", itemList[viewHolder.adapterPosition].id)
                        context.startActivity(editIntent)


                    }
                    "취소" -> {
                    }
                    else -> {
                        Log.d("SetOnLongClickListener", "item position error!")
                    }

                }
            }
            builder.show()
            false
        }

        return viewHolder
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.onbind(itemList[position])
    }




}