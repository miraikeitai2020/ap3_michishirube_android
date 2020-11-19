package com.example.michishirube.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RegisteredSpotsEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase(){
    abstract fun registeredSpotsDao(): RegisteredSpotsDao
}