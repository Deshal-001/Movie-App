package com.example.coursework2022.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.coursework2022.Converters

@Database (entities = [Movie::class], version=1)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase(){
    abstract fun movieDao(): MovieDao


    companion object {
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase? {
            if (INSTANCE == null) {
                synchronized(MovieDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        MovieDatabase::class.java, "movieDB.db").allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}