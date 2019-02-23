package com.example.khurusn.myapplication.user_list

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Button
import com.example.khurusn.myapplication.R
import com.example.khurusn.myapplication.add_applicant.AddAplicantActivity
import kotlinx.android.synthetic.main.activity_user_list.*

class UserListActivity : AppCompatActivity(), IUserListView {

    private val TAG: String = "UserListActivity"
    private lateinit var mUserListPresenter: UserListPresenter
    private lateinit var mUserListAdapter: UserListAdapter
    private lateinit var mUserList: RecyclerView
    private lateinit var mBtnAddUser: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        mUserListPresenter = UserListPresenter(this)
        mUserListPresenter.getUserList()

        mBtnAddUser = btnAddUser

        mBtnAddUser.setOnClickListener {
            startActivity(Intent(this, AddAplicantActivity::class.java))
        }
    }

    override fun showProgress() {

    }

    override fun stopProgress() {
    }

    override fun usersSuccess(usersResponse: UsersResponse) {
        Log.d(TAG, "usersSuccess() :: $usersResponse")

        mUserListAdapter = UserListAdapter(this, usersResponse.data!!)
        mUserList = recyclerUsers
        mUserList.layoutManager = LinearLayoutManager(this)
        mUserList.adapter = mUserListAdapter
    }

    override fun userFailure() {
        Log.d(TAG, "userFailure()")
    }
}
