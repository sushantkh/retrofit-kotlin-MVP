package com.example.khurusn.myapplication.login

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import com.example.khurusn.myapplication.R
import com.example.khurusn.myapplication.user_list.UserListActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), ILoginView {
    private val TAG: String = "LoginActivity"
    private lateinit var mLoginPresenter: LoginPresenter
    private lateinit var textInputUserName: TextInputEditText
    private lateinit var textInputPassword: TextInputEditText
    private lateinit var btnLogin: Button
    private lateinit var loginProgress: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mLoginPresenter = LoginPresenter(this)

        textInputUserName = inputUserName
        textInputPassword = inputPassword
        btnLogin = button
        loginProgress = loginProgressBar

        btnLogin.setOnClickListener {
            var loginRequest = LoginRequest()
            loginRequest.email = textInputUserName.text.toString()
            loginRequest.password = textInputPassword.text.toString()
            Log.d(TAG, "login request $loginRequest")
            mLoginPresenter.authenticateUser(loginRequest)
        }
    }


    override fun showProgress() {
        loginProgress.visibility = View.VISIBLE
    }

    override fun stopProgress() {
        loginProgress.visibility = View.INVISIBLE
    }

    override fun loginSuccess(loginResponse: LoginResponse) {
        Log.d(TAG, "response $loginResponse")
        startActivity(Intent(this, UserListActivity::class.java))
        Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
        this.finish()
    }

    override fun loginFailure() {
        Log.d(TAG, "login failed")
    }
}
