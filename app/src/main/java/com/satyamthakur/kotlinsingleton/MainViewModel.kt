package com.satyamthakur.kotlinsingleton

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.satyamthakur.kotlinsingleton.models.Todo
import com.satyamthakur.kotlinsingleton.repository.MainRepository

class MainViewModel: ViewModel() {
    private val _todoId: MutableLiveData<String> = MutableLiveData()
    val todo: LiveData<Todo> = Transformations
        .switchMap(_todoId){
            MainRepository.getTodo(it)
        }

    fun setTodoId(todoId: String) {
        val update = todoId
        if(_todoId.value == update) {
            return
        }
        _todoId.value = update
    }

    fun cancelJobs() {
        MainRepository.cancelJobs()
    }
}