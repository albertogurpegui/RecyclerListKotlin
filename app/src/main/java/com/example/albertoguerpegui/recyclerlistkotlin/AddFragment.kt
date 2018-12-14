package com.example.albertoguerpegui.recyclerlistkotlin

import android.app.AlertDialog
import android.app.Dialog
import android.app.PendingIntent.getActivity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class AddFragment : DialogFragment() {

    var name: TextView? = null
    var surname: TextView? = null
    var address: TextView? = null
    private var mCallback: OnFragmentListener? = null
    private var param1: User? = null

    interface OnFragmentListener{
        fun addUser(user: User)
        fun removeUser(user: User)
    }

    companion object {
        const val KEY = "USER"
        fun newInstance(user: User) = AddFragment().apply {
            arguments = Bundle().apply {
                    putParcelable(KEY, user)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var mview = getActivity()!!.getLayoutInflater().inflate(R.layout.add_user_fragment, null)

        arguments?.let {
            param1 = it.getParcelable(KEY)
        }

        name = mview.findViewById(R.id.name_user)
        surname = mview.findViewById(R.id.surname_user)
        address = mview.findViewById(R.id.address_user)

        name?.text = param1?.name
        surname?.text = param1?.surname
        address?.text = param1?.address

        val add = mview.findViewById<Button>(R.id.btn_add)
        add.setOnClickListener {
            addUser(name?.text.toString(),
                    surname?.text.toString(),
                    address?.text.toString())
            dismiss()
        }

        val remove  = mview.findViewById<Button>(R.id.btn_remove)
        remove.setOnClickListener {
            removeUser()
            dismiss()
        }

        val update  = mview.findViewById<Button>(R.id.btn_update)
        update.setOnClickListener {
            updateUser(name?.text.toString(),
                surname?.text.toString(),
                address?.text.toString())
            dismiss()
        }

        var alert = AlertDialog.Builder(activity)
        alert.setView(mview)

        return alert.create()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mCallback = context as OnFragmentListener
    }

    fun addUser(name: String, surname: String, address: String) {
        val user = User(name, surname, address)
        mCallback?.addUser(user)
    }

    fun removeUser() {
        //user = User(name, surname, address)
        mCallback?.removeUser(param1!!)
    }

    fun updateUser(name: String, surname: String, address: String) {
        mCallback?.removeUser(param1!!)
        param1?.name = name
        param1?.surname = surname
        param1?.address = address
        mCallback?.addUser(param1!!)
    }
}