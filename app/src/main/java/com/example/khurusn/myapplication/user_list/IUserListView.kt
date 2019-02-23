package com.example.khurusn.myapplication.user_list

interface IUserListView{

    fun showProgress()
    fun stopProgress()
    fun usersSuccess(usersResponse: UsersResponse)
    fun userFailure()
}