package com.satyamthakur.kotlinsingleton.repository

import com.satyamthakur.kotlinsingleton.models.QuoteResponse

sealed class Response<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(): Response<T>()
    class Success<T>(data: T? = null): Response<T>(data = data)
    class Error<T>(errorMessage: String): Response<T>(message = errorMessage)
}