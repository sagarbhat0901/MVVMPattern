package com.example.mvvmpattern

import androidx.lifecycle.LiveData

interface AuthInterface {

    fun onStarted()
    fun onFailure(message : String)
    fun onSuccess(success: LiveData<String>)
}