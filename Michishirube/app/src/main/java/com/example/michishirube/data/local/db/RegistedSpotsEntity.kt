package com.example.michishirube.data.local.db
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spot")
data class RegistedSpotsEntity (
    //スポット情報書く場所
    @PrimaryKey(autoGenerate = true)
    val spotId:Int,
    val spotName: String,
    val spotEmotion: Int,
    val spotDesc: String,
    val spotLatitude: Double,
    val spotLongitude: Double

)