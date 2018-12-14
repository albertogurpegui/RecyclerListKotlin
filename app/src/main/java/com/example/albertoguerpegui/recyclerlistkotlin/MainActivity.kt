package com.example.albertoguerpegui.recyclerlistkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AddFragment.OnFragmentListener, UserAdapter.OnClickItem{

    val users: ArrayList<User> = ArrayList()
    var mAdapter: UserAdapter? = null

    override fun addUser(user: User) {
        users.add(user)
        mAdapter.let {
            it?.notifyDataSetChanged()
        }
    }

    override fun removeUser(user: User) {
        users.remove(user)
        mAdapter.let {
            it?.notifyDataSetChanged()
        }
    }


    override fun getUser(user: User) {
        var otherDialog = AddFragment.newInstance(user)
        otherDialog.show( supportFragmentManager, "otherDialog")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pressAddButton()

        rv_user_list.layoutManager = LinearLayoutManager(this)
        mAdapter = UserAdapter(users, this)
        mAdapter?.setCallback(this)
        rv_user_list.adapter = mAdapter





    }

    fun pressAddButton() {
        val fab = findViewById<FloatingActionButton>(R.id.button)
        fab.setOnClickListener {
            val pop = AddFragment()
            pop.show( supportFragmentManager , "addDialog")
        }
    }
}
