package com.example.michishirube.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.michishirube.models.Spot
import java.util.concurrent.Flow

@Dao
interface RegisteredSpotsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(spot: SpotsEntity)

    @Query("SELECT * FROM spot_table")
    fun getAllSpots(): LiveData<List<SpotsEntity>>

}