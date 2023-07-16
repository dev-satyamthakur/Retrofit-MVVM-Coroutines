package com.satyamthakur.kotlinsingleton.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.satyamthakur.kotlinsingleton.api.RetrofitService
import com.satyamthakur.kotlinsingleton.db.QuoteDatabase
import com.satyamthakur.kotlinsingleton.models.QuoteResponse
import com.satyamthakur.kotlinsingleton.utils.NetworkUtils

class MainRepository(
    private val quoteService: RetrofitService,
    private val quoteDatabase: QuoteDatabase,
    private val context: Context
) {

    private val quotesLiveData = MutableLiveData<QuoteResponse>()

    val quotes: LiveData<QuoteResponse>
        get() = quotesLiveData

    suspend fun getQuotes(page: Int) {

        if (NetworkUtils.isNetworkAvailable(context)) {
            val result = quoteService.getQuotes(page)
            if (result?.body() != null) {
                quoteDatabase.getQuoteDao().addQuotes(result.body()!!.results)
                quotesLiveData.postValue(result.body())
            }
        } else {
            val quotes = quoteDatabase.getQuoteDao().getQoutes()
            val quoteList = QuoteResponse(1, 1, 1, quotes, 1, 1)
            quotesLiveData.postValue(quoteList)
        }

    }

}