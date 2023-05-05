package com.satyamthakur.kotlinsingleton.repository

import androidx.lifecycle.LiveData
import com.satyamthakur.kotlinsingleton.api.MyRetrofitBuilder
import com.satyamthakur.kotlinsingleton.models.Todo
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object MainRepository {

    var job: CompletableJob? = null

    fun getTodo(todoId: String): LiveData<Todo> {

        job = Job()

        return object: LiveData<Todo>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val todo = MyRetrofitBuilder.apiService.getTodo(todoId)
                        withContext(Main) {
                            value = todo
                            theJob.complete()
                        }
                    }
                }
            }
        }
    }

    fun cancelJobs() {
        job?.cancel()
    }

}