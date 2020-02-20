package com.example.kotlinstudy.main.todo

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinstudy.R
import com.example.kotlinstudy.add_edit.AddEditTodoActivity
import com.example.kotlinstudy.room.database.MyDatabase
import com.example.kotlinstudy.room.entity.DoneItem
import com.example.kotlinstudy.room.entity.TodoItem
import kotlinx.android.synthetic.main.activity_add_edit_todo.*
import kotlinx.android.synthetic.main.fragment_todo.*
import kotlinx.android.synthetic.main.item_todo.*
import java.text.SimpleDateFormat
import java.util.*

@Suppress("UNREACHABLE_CODE")
class TodoFragment : Fragment() {

    private var adapter: TodoAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TodoAdapter(view.context)
        main_rcv_item.adapter = adapter
        main_rcv_item.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)

        main_fab_add.setOnClickListener {

            val addIntent = Intent(view.context, AddEditTodoActivity::class.java)
            addIntent.putExtra("mode", AddEditTodoActivity.MODE_ADD)
            startActivity(addIntent)
        }

        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        adapter?.refresh()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_checked, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
      



        when (item?.itemId) {
            R.id.menu_checked -> {
                var myDatabase = MyDatabase.getInstance(context!!)
                myDatabase?.todoDao()?.getTodoDone()?.let{
                    for(item in it){

                        myDatabase.doneDao().insertDone(changeDoneItem(item))
                        myDatabase.todoDao().deleteTodo(item)
                    }

                    adapter?.refresh()
                }


            }


        }
        return super.onOptionsItemSelected(item)

    }

    private fun changeDoneItem(item : TodoItem) : DoneItem{
        val today = SimpleDateFormat("yyyy / MM / dd").format(Date())
        return DoneItem(0,item.name,item.sDate,item.dDate,item.memo,today)

    }
}
