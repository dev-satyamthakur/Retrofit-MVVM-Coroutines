package com.satyamthakur.kotlinsingleton.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.satyamthakur.kotlinsingleton.api.RetrofitService
import com.satyamthakur.kotlinsingleton.models.QuoteResponse

class MainRepository(private val quoteService: RetrofitService) {

    private val quotesLiveData = MutableLiveData<QuoteResponse>()

    val quotes: LiveData<QuoteResponse>
        get() = quotesLiveData

    suspend fun getQuotes(page: Int) {
        val result = quoteService.getQuotes(page)
        if (result?.body() != null) {
            quotesLiveData.postValue(result.body())
        }
    }

}