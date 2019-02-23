package com.example.khurusn.myapplication.login

import com.example.khurusn.myapplication.utility.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter {
    private var mILoginView: ILoginView
    private var mAPIService: APIService

    constructor(iLoginView: ILoginView) {
        mILoginView = iLoginView
        mAPIService = APIService.create()
    }

    public fun authenticateUser(loginRequest: LoginRequest) {
        val loginResponse: Call<LoginResponse> = mAPIService.loginUser(loginRequest)
        mILoginView.showProgress()
        loginResponse.enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
                mILoginView.stopProgress()
                mILoginView.loginFailure()
            }

            override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {
                mILoginView.stopProgress()
                response!!.body()?.let { mILoginView.loginSuccess(it) }
            }
        })
    }
}