package com.example.michishirube.data.local

import androidx.lifecycle.LiveData
import com.example.michishirube.data.SpotDataSource
import com.example.michishirube.data.local.db.AppDatabase
import com.example.michishirube.data.local.db.Application.Companion.database
import com.example.michishirube.data.local.db.RegisteredSpotsEntity
import com.example.michishirube.models.Spot

class LocalDataSource (private val database: AppDatabase): SpotDataSource {
    override fun loadAllSpots(): LiveData<List<Spot>> {
        return database.registeredSpotsDao().getSpot().map{
            spot -> spot.map{ Spot(it.id, it.name, it.emotion, it.desc, it.latitude, it.longitude) }
        }
    }

    override fun inputSpot(spot: Spot) {
        val forInsertSpot = RegisteredSpotsEntity.createForInsert(
            spot.id,
            spot.name,
            spot.emotion,
            spot.desc,
            spot.latitude,
            spot.longitude
        )
        database.registeredSpotsDao().insert(forInsertSpot)
    }
}