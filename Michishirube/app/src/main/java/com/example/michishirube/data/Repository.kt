package com.example.michishirube.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.michishirube.data.local.db.RegisteredSpotsDao
import com.example.michishirube.data.local.db.SpotsEntity

class SpotRepository(private val registeredSpotsDao: RegisteredSpotsDao){

    val allSpots: LiveData<List<SpotsEntity>> = registeredSpotsDao.getAllSpots()

    @WorkerThread
    suspend fun insert(spot:SpotsEntity){
        registeredSpotsDao.insert(spot)
    }
}