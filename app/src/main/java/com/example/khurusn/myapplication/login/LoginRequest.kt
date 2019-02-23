package com.example.khurusn.myapplication.login

class LoginRequest {
    var password: String? = null

    var email: String? = null

    override fun toString(): String {
        return "LoginResponse [password = $password, email = $email]"
    }
}