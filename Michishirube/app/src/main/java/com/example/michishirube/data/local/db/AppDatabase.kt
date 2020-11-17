package com.example.michishirube.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RegistedSpotsEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase(){
    abstract fun registedSpotsDao(): RegistedSpotsDao
}