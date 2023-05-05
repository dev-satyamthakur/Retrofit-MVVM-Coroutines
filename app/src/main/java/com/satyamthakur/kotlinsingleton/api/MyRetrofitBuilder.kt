package com.satyamthakur.kotlinsingleton.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyRetrofitBuilder {

    const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiService: RetrofitService by lazy {
        retrofitBuilder
            .build()
            .create(RetrofitService::class.java)
    }

}