package com.example.michishirube.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SpotsEntity::class], version = 1, exportSchema = false)
abstract class SpotDatabase: RoomDatabase(){
    abstract fun registeredSpotsDao(): RegisteredSpotsDao

    companion object{
        @Volatile
        private var INSTANCE: SpotDatabase? = null

        fun getDatabase(context: Context): SpotDatabase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SpotDatabase::class.java,
                    "michishirube_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}