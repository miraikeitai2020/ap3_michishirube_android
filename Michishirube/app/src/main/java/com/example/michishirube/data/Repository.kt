package com.example.michishirube.data

import androidx.lifecycle.LiveData
import com.example.michishirube.data.local.LocalDataSource
import com.example.michishirube.models.Spot

interface Repository{
    suspend fun inputSpot(spot: Spot)
    fun loadAllSpots(): LiveData<List<Spot>>
}

class DefaultRepository(
    private val localDataSource: LocalDataSource
): Repository {

    override suspend fun inputSpot(spot: Spot){
        localDataSource.inputSpot(spot)
    }

    override fun loadAllSpots(): LiveData<List<Spot>>{
        val localSpotList = localDataSource.loadAllSpots()
        return localSpotList
    }


}