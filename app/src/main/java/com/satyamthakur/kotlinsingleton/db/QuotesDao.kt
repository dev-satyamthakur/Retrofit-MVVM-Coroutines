package com.satyamthakur.kotlinsingleton.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.satyamthakur.kotlinsingleton.models.Result

@Dao
interface QuotesDao {

    @Insert
    suspend fun addQuotes(quotes: List<Result>)

    @Query("SELECT * FROM quotes")
    suspend fun getQoutes(): List<Result>

}