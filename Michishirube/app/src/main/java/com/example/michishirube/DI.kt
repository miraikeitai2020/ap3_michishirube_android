package com.example.michishirube

import android.content.Context
import androidx.room.Room
import com.example.michishirube.data.DefaultRepository
import com.example.michishirube.data.Repository
import com.example.michishirube.data.local.LocalDataSource
import com.example.michishirube.data.local.db.AppDatabase

object DI {
    fun injectRepository(context: Context): Repository {
        val localDataSource = injectLocalDataSource(context)

        return DefaultRepository(localDataSource)
    }

    private fun injectLocalDataSource(context: Context): LocalDataSource {
        val database = injectDatabase(context)

        return LocalDataSource(database)
    }

    private fun injectDatabase(context: Context): AppDatabase {
        val database = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "michishirube_database"
        ).build()

        return database
    }
}