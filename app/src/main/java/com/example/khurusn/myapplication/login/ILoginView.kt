package com.example.khurusn.myapplication.login

interface ILoginView {

    fun showProgress()
    fun stopProgress()
    fun loginSuccess(loginResponse: LoginResponse)
    fun loginFailure()
}