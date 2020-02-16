package com.example.kotlinstudy.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.example.kotlinstudy.R
import com.example.kotlinstudy.main.done.DoneFragment
import com.example.kotlinstudy.main.todo.TodoFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_done,menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment1 = TodoFragment()
        val fragment2 = DoneFragment()
        val doneMenu = R.menu.menu_checked





        supportActionBar?.title = "Todo list"
        supportFragmentManager.beginTransaction().replace(R.id.main_frameLayout, fragment1)
            .commitAllowingStateLoss()




        main_bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_main_todo -> {
                    supportActionBar?.title = "Todo list"
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frameLayout, fragment1).commitAllowingStateLoss()


                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_main_done -> {

                    supportActionBar?.title = "Done list"
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frameLayout, fragment2).commitAllowingStateLoss()
                    supportFragmentManager.beginTransaction().add(R.id



                    return@setOnNavigationItemSelectedListener true
                }

                else -> {
                    return@setOnNavigationItemSelectedListener true
                }
            }
        }

    }
}