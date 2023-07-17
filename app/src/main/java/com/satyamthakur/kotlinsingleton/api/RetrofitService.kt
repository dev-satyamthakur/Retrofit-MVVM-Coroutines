package com.satyamthakur.kotlinsingleton.api

import com.satyamthakur.kotlinsingleton.models.QuoteResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("/quotess")
    suspend fun getQuotes(@Query("page") page: Int): Response<QuoteResponse>
}