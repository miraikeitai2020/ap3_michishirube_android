package com.example.michishirube.data

import androidx.lifecycle.LiveData
import com.example.michishirube.models.Spot

interface SpotDataSource{
    fun loadAllSpots(): LiveData<List<Spot>>

    fun inputSpot(spot: Spot)
}