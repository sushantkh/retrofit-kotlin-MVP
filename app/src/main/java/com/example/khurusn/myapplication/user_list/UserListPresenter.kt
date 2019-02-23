package com.example.khurusn.myapplication.user_list

import com.example.khurusn.myapplication.utility.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserListPresenter {
    private var mUserListView: IUserListView
    private var mAPIService: APIService

    constructor(iUserListView: IUserListView) {
        mUserListView = iUserListView
        mAPIService = APIService.create()
    }

    public fun getUserList() {
        val usersResponse: Call<UsersResponse> = mAPIService.getUserList()
        mUserListView.showProgress()
        usersResponse.enqueue(object : Callback<UsersResponse> {
            override fun onFailure(call: Call<UsersResponse>?, t: Throwable?) {
                mUserListView.stopProgress()
                mUserListView.userFailure()
            }

            override fun onResponse(call: Call<UsersResponse>?, response: Response<UsersResponse>?) {
                mUserListView.stopProgress()
                response!!.body()?.let { mUserListView.usersSuccess(it) }
            }
        })
    }
}