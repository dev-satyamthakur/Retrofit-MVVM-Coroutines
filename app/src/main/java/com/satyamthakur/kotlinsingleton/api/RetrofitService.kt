package com.satyamthakur.kotlinsingleton.api

import com.satyamthakur.kotlinsingleton.models.Todo
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("todos/{todo_id}")
    suspend fun getTodo(@Path("todo_id") todoId: String): Todo

}