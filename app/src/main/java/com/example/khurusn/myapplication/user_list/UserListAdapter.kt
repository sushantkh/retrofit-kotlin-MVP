package com.example.khurusn.myapplication.user_list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.khurusn.myapplication.R

class UserListAdapter(context: Context, userList: Array<UsersResponse.Data>) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    private var mContext: Context = context
    private var mUserList: Array<UsersResponse.Data> = userList

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): UserViewHolder {
        var view: View = LayoutInflater.from(mContext).inflate(R.layout.user_row, p0, false)

        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mUserList.size
    }

    override fun onBindViewHolder(viewHolder: UserViewHolder, p1: Int) {
        viewHolder.tvName.text = mUserList.get(p1).first_name
        viewHolder.tvLastName.text = mUserList.get(p1).last_name
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.tvName)
        val tvLastName = itemView.findViewById<TextView>(R.id.tvLastName)
        val tvLoanAmount = itemView.findViewById<TextView>(R.id.tvLoanAmount)
    }
}