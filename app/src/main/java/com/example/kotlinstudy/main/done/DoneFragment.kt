package com.example.kotlinstudy.main.done

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Database
import com.example.kotlinstudy.R
import com.example.kotlinstudy.room.database.MyDatabase
import kotlinx.android.synthetic.main.fragment_done.*
import java.util.zip.Inflater

class DoneFragment: Fragment(){

    private var adapter = DoneAdapter? = null
    private val myDatabase: MyDatabase? = MyDatabase.getInstance(context!!)




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = DoneAdapter(view.context)
        done_rcv_item.adapter = adapter
        done_rcv_item.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL,false)

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_done, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.menu_done_delete ->{

            }
            R.id.menu_done_refresh->{

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
}