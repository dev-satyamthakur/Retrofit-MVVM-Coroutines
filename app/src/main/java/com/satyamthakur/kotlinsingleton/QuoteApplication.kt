package com.satyamthakur.kotlinsingleton

import android.app.Application
import com.satyamthakur.kotlinsingleton.api.MyRetrofitBuilder
import com.satyamthakur.kotlinsingleton.api.RetrofitService
import com.satyamthakur.kotlinsingleton.db.QuoteDatabase
import com.satyamthakur.kotlinsingleton.repository.MainRepository

class QuoteApplication: Application() {

    lateinit var quoteRepository: MainRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteSer = MyRetrofitBuilder.getInstance().create(RetrofitService::class.java)
        val db = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = MainRepository(quoteSer, db)
    }

}