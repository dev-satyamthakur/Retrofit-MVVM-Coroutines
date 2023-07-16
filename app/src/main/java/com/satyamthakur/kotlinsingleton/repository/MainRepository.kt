package com.satyamthakur.kotlinsingleton.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.satyamthakur.kotlinsingleton.api.RetrofitService
import com.satyamthakur.kotlinsingleton.db.QuoteDatabase
import com.satyamthakur.kotlinsingleton.models.QuoteResponse

class MainRepository(
    private val quoteService: RetrofitService,
    private val quoteDatabase: QuoteDatabase) {

    private val quotesLiveData = MutableLiveData<QuoteResponse>()

    val quotes: LiveData<QuoteResponse>
        get() = quotesLiveData

    suspend fun getQuotes(page: Int) {
        val result = quoteService.getQuotes(page)
        if (result?.body() != null) {
            quoteDatabase.getQuoteDao().addQuotes(result.body()!!.results)
            quotesLiveData.postValue(result.body())
        }
    }

}