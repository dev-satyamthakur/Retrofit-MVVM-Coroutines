package com.satyamthakur.kotlinsingleton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.satyamthakur.kotlinsingleton.ViewModel.MainViewModel
import com.satyamthakur.kotlinsingleton.ViewModel.MainViewModelFactory
import com.satyamthakur.kotlinsingleton.api.MyRetrofitBuilder
import com.satyamthakur.kotlinsingleton.api.RetrofitService
import com.satyamthakur.kotlinsingleton.repository.MainRepository

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repo = (application as QuoteApplication).quoteRespository

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repo))
            .get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this, Observer {
            Log.d("MYAPP", it.toString())
        })

    }
}