package com.satyamthakur.kotlinsingleton.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satyamthakur.kotlinsingleton.models.QuoteResponse
import com.satyamthakur.kotlinsingleton.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository): ViewModel() {

    val quotes: LiveData<QuoteResponse>
        get() = repository.quotes

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuotes(1)
        }
    }

}