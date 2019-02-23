package com.example.khurusn.myapplication.utility

import com.example.khurusn.myapplication.login.LoginRequest
import com.example.khurusn.myapplication.login.LoginResponse
import com.example.khurusn.myapplication.user_list.UsersResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService{
    @POST("login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @GET("users?page=2")
    fun getUserList():Call<UsersResponse>

    companion object Factory {
        fun create(): APIService {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.BASE_URl)
                    .build()

            return retrofit.create(APIService::class.java)
        }
    }
}