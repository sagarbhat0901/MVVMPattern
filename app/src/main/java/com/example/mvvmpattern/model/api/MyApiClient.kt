package com.example.mvvmpattern.model.api

import com.example.mvvmpattern.model.data.UserData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface MyApiClient  {

    @POST("api/login")
    fun ApiClientUserLogin(@Body userData : UserData): Call<ResponseBody>


    companion object{
        fun MyGetResponse() : MyApiClient {
            val myRetrofit = Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApiClient::class.java)

            return myRetrofit
        }
    }
}