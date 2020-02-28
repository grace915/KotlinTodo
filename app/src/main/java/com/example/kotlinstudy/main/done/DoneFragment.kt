package com.example.kotlinstudy.main.done

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Database
import com.example.kotlinstudy.R
import com.example.kotlinstudy.main.todo.TodoAdapter
import com.example.kotlinstudy.room.dao.DoneDao
import com.example.kotlinstudy.room.database.MyDatabase
import com.example.kotlinstudy.room.entity.DoneItem
import kotlinx.android.synthetic.main.fragment_done.*
import kotlinx.android.synthetic.main.item_done.*
import kotlinx.android.synthetic.main.item_todo.*
import java.util.zip.Inflater

class DoneFragment : Fragment() {

    private var fragment: DoneFragment? = null
    private var adapter: DoneAdapter? = null
    private var myDatabase: MyDatabase? = null
    private var menu: Menu? = null

    var itemList: MutableList<DoneItem> = mutableListOf()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myDatabase = MyDatabase.getInstance(view.context)
        adapter = DoneAdapter(view.context)
        done_rcv_item.adapter = adapter
        done_rcv_item.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        setHasOptionsMenu(true)

    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_done, menu)

        this.menu = menu
        menu?.findItem(R.id.menu_confirm)?.isVisible = false
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.menu_done_delete -> {
                menu?.apply {
                    convertMenuVisibility(findItem(R.id.menu_confirm))
                    convertMenuVisibility(findItem(R.id.menu_done_delete))
                    convertMenuVisibility(findItem(R.id.menu_done_deleteAll))
                }
//                adapter?.deleteItem()
            }
            R.id.menu_done_deleteAll -> {

                myDatabase?.doneDao()?.deleteAll()
                itemList.clear()
                adapter?.refresh()

            }


        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MyDatabase.getInstance(context!!)
        return inflater.inflate(R.layout.fragment_done, container, false)
    }

    override fun onResume() {
        super.onResume()
        adapter?.refresh()
    }

    private fun convertMenuVisibility(menuItem: MenuItem) {
        menuItem.isVisible = !menuItem.isVisible
    }

}