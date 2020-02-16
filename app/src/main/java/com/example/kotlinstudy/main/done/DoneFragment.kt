package com.example.kotlinstudy.main.done

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinstudy.R
import kotlinx.android.synthetic.main.fragment_done.*
import java.util.zip.Inflater

class DoneFragment: Fragment(){

    private var adapter = DoneAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = DoneAdapter(view.context)
        done_rcv_item.adapter = adapter
        done_rcv_item.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL,false)



    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     return inflater.inflate(R.layout.fragment_done,container,false)
    }
}