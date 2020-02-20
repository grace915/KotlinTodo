package com.example.kotlinstudy.main.done

import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinstudy.R
import com.example.kotlinstudy.room.entity.DoneItem

import kotlinx.android.synthetic.main.fragment_done.view.*
import kotlinx.android.synthetic.main.item_collection.view.*

import java.text.SimpleDateFormat
import java.util.*

class DoneViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){



    fun onbind(item: DoneItem) {
        itemView.done_cb.isChecked = item.checked.also{
            if(it){
                itemView.done_tv_name.paintFlags = itemView.done_tv_name.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }else{
                itemView.done_tv_name.paintFlags =0

            }
        }
        itemView.done_tv_name.text = item.name
        itemView.done_tv_due.text = item.dDate




       itemView.done_tv_due.text = item.dDate
       itemView.done_tv_done_date.text = item.doneDate
    }


 }

