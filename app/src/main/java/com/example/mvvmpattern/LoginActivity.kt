package com.example.mvvmpattern

import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.mvvmpattern.databinding.ActivityLoginBinding
import com.example.mvvmpattern.view.AuthInterface
import com.example.mvvmpattern.viewmodel.AuthViewModel


class LoginActivity : AppCompatActivity(), AuthInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding : ActivityLoginBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_login
        )
        // Create ViewModelProvider instance
        val viewModelProvider = ViewModelProvider(this)

        // Get ViewModel using the ViewModelProvider
        val myViewModel = viewModelProvider.get(AuthViewModel::class.java)
        binding.layoutViewmodel = myViewModel
        myViewModel.authlistner = this

    }

    override fun onStarted() {
        Toast.makeText(this,"Its STARTED",Toast.LENGTH_SHORT).show()
    }

    override fun onFailure(message: String) {
        Toast.makeText(this,"Its failuer $message",Toast.LENGTH_SHORT).show()

    }

    override fun onSuccess(success: LiveData<String>) {
        success.observe(this, Observer {
            Toast.makeText(this,"made network call $success" + it,Toast.LENGTH_SHORT).show()

        })

    }
}