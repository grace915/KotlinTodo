package com.example.kotlinstudy.main.done

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Database
import com.example.kotlinstudy.R
import com.example.kotlinstudy.main.todo.TodoAdapter
import com.example.kotlinstudy.room.database.MyDatabase
import com.example.kotlinstudy.room.entity.DoneItem
import kotlinx.android.synthetic.main.fragment_done.*
import kotlinx.android.synthetic.main.item_collection.*
import kotlinx.android.synthetic.main.item_todo.*
import java.util.zip.Inflater

class DoneFragment: Fragment(){

    private var adapter :  DoneAdapter? = null
    private val myDatabase: MyDatabase? = null
    var itemList: MutableList<DoneItem> = mutableListOf()
    //does not have a companion obgect, and thus must be initialized here

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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = DoneAdapter(view.context)
        done_rcv_item.adapter = adapter
        done_rcv_item.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL,false)

    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_done, menu)

    }

   override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId){
            R.id.menu_done_select->{


            }
            R.id.menu_done_deleteAll->{

                deleteItem()

            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     return inflater.inflate(R.layout.fragment_done,container,false)
    }

    override fun onResume() {
        super.onResume()
        adapter?.refresh()
    }



}