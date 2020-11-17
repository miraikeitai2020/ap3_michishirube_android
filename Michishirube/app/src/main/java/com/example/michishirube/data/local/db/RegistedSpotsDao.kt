package com.example.michishirube.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RegistedSpotsDao {
    @Insert
    fun insert(spot: RegistedSpotsEntity)

    @Query("SELECT * FROM spot")
    fun getSpot(): LiveData<List<RegistedSpotsEntity>>


}