package com.example.michishirube.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RegisteredSpotsDao {
    @Insert
    fun insert(spotsEntity: RegisteredSpotsEntity)

    @Query("SELECT * FROM spot")
    fun getSpot(): LiveData<List<RegisteredSpotsEntity>>

}