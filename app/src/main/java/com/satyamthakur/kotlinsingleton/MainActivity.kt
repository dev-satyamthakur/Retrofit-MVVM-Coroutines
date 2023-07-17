package com.satyamthakur.kotlinsingleton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.satyamthakur.kotlinsingleton.ViewModel.MainViewModel
import com.satyamthakur.kotlinsingleton.ViewModel.MainViewModelFactory
import com.satyamthakur.kotlinsingleton.repository.Response

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repo = (application as QuoteApplication).quoteRepository

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repo))
            .get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this, Observer {
            Log.d("MYAPP", it.toString())
            when (it) {
                is Response.Loading -> {
                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                }

                is Response.Success -> {
                    it.data?.let {
                        Toast.makeText(this, it.results.size.toString(), Toast.LENGTH_SHORT).show()
                    }
                }

                is Response.Error -> {
                    Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })

    }
}