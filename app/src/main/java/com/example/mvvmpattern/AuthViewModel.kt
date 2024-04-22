package com.example.mvvmpattern

import UserRepo
import android.view.View
import androidx.lifecycle.ViewModel


class AuthViewModel : ViewModel() {

    var email:String ? = null
    var pwd:String ? = null

    var authlistner : AuthInterface? = null
    fun onLoginButtonClicked(view : View){
        authlistner?.onStarted()
        if(email.isNullOrEmpty() && pwd.isNullOrEmpty()){
            authlistner?.onFailure("Its failed")
        }
        else {
            var userData = UserData(email!!,pwd!!)
            val myreponse = UserRepo().userLogin(userData)
            authlistner?.onSuccess(myreponse)

        }
    }
}