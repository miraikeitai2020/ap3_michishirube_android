package com.example.michishirube.models

data class Spot(
    val id: Int,
    val name: String,
    val emotion: Int,
    val desc: String,
    val latitude: Double,
    val longitude: Double
//,val image
)