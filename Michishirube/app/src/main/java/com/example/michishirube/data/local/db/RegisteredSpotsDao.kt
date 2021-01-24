package com.example.michishirube.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface RegisteredSpotsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(spot: SpotsEntity)

    @Query("SELECT * FROM spot_table")
    fun getAllSpots(): LiveData<List<SpotsEntity>>

}