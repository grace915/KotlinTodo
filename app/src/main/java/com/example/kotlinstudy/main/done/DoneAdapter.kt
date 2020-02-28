package com.example.kotlinstudy.main.done

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinstudy.R
import com.example.kotlinstudy.room.database.MyDatabase
import com.example.kotlinstudy.room.entity.DoneItem


class DoneAdapter(private val context: Context) : RecyclerView.Adapter<DoneViewHolder>()  {

    private val id : Int? = null
    private val myDatabase: MyDatabase? = MyDatabase.getInstance(context)
    private var itemList: MutableList<DoneItem> = mutableListOf()

    init {
         myDatabase?.doneDao()?.getDones()?.also {
            itemList.addAll(it)
        }

        notifyDataSetChanged()
    }



    fun deleteItem(){
        myDatabase?.doneDao()?.deleteCheckedItem()
        refresh()
    }

    fun refresh() {

        myDatabase?.doneDao()?.getDones()?.also {
            itemList.clear()
            itemList.addAll(it)

        }
        notifyDataSetChanged()
    }


    override fun getItemViewType(position: Int): Int {
        return when(id){
            R.id.menu_confirm ->{
                0
            }
            else ->{
                1
            }
        }
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoneViewHolder {

        when (viewType) {
            0 -> {
                var viewHolder = DoneViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_done_check,
                        parent,
                        false
                    )
                )

                viewHolder.itemView.setOnClickListener {

                    itemList[viewHolder.adapterPosition].also {

                        it.checked = !it.checked
                        myDatabase?.doneDao()?.updateDone(it)

                    }
                    notifyDataSetChanged()

                }
                return viewHolder
            }
            else -> {
                var viewHolder = DoneViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_done,
                        parent,
                        false
                    )
                )
                return viewHolder
            }



        }



    }






    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: DoneViewHolder, position: Int) {
        holder.onbind(itemList[position])
    }



}