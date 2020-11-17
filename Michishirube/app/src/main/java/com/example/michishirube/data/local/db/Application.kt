package com.example.michishirube.data.local.db

import android.app.Application
import androidx.room.Room

class Application: Application(){
    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate(){
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "michishirube_database"
        ).build()

    }
}