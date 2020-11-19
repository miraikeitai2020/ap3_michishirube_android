package com.example.michishirube.data.local.db
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spot")
data class RegisteredSpotsEntity (
    @PrimaryKey(autoGenerate = true) val spotId:Int,
    @ColumnInfo(name = "name") val spotName: String,
    @ColumnInfo(name = "emotion") val spotEmotion: Int,
    @ColumnInfo(name = "desc") val spotDesc: String,
    @ColumnInfo(name = "latitude") val spotLatitude: Double,
    @ColumnInfo(name = "longitude") val spotLongitude: Double
//,val image
){
    companion object {
        fun createForInsert(id:Int, name: String, emotion: Int, desc: String, latitude: Double, longitude: Double): RegisteredSpotsEntity{
            return RegisteredSpotsEntity(
                id,
                name,
                emotion,
                desc,
                latitude,
                longitude
            )
        }
    }
}