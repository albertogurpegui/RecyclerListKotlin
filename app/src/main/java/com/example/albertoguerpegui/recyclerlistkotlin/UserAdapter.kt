package com.example.albertoguerpegui.recyclerlistkotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.user_list_item.view.*

class UserAdapter(val items : ArrayList<User>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    private var mCallback: OnClickItem? = null

    interface OnClickItem{
        fun getUser(user: User)
    }

    fun setCallback(callback: OnClickItem){
        mCallback = callback
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.user_list_item, p0, false))
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val user = items[p1]
        p0?.tvNameUser?.text = user.name
        p0?.tvSurnameUser?.text = user.surname
        p0?.tvAddressUser?.text = user.address
        p0?.itemView.setOnClickListener {
           mCallback?.let {callback ->
               if ( user != null){
                   callback.getUser(user)
               }
           }
        }

    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvNameUser = view.tv_name_type
    val tvSurnameUser = view.tv_surname_type
    val tvAddressUser = view.tv_address_type
}