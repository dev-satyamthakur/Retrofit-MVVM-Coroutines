package com.satyamthakur.kotlinsingleton.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.satyamthakur.kotlinsingleton.models.Result

@Database(entities = [Result::class,], version = 1)
abstract class QuoteDatabase: RoomDatabase() {

    abstract fun getQuoteDao(): QuotesDao

    companion object {
        @Volatile
        private var INSTANCE: QuoteDatabase? = null

        fun getDatabase(context: Context): QuoteDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context,
                         QuoteDatabase::class.java, "quote_db")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }

}