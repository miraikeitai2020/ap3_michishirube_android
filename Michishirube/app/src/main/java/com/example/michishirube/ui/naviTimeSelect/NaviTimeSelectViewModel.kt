package com.example.michishirube.ui.naviTimeSelect

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult

class NaviTimeSelectViewModel: ViewModel() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallBack: LocationCallback

    var lat = 0.0
    var lon = 0.0

    fun setLocation(myLat: Double?, myLon: Double?){
        lat = myLat!!
        lon = myLon!!
    }

    private fun checkRequestPermission(activity: Activity, context: Context){
        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
            ActivityCompat.requestPermissions(activity,permissions,1000)
        }
    }

    fun getDeviceLocation(activity: Activity, context: Context){
        fusedLocationClient = FusedLocationProviderClient(context)
        checkRequestPermission(activity,context)

        //初回取得時は，最後の位置情報を利用
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if(location != null){
                setLocation(location.latitude, location.longitude)
            }else{
                //位置情報の取得に失敗しました，的な何かを出したい
                //UIに関わる部分なのでiOSやデザインと話し合いが必要だと思ったので保留
            }
        }

        locationCallBack = object : LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult?) {
                super.onLocationResult(locationResult)
                if(locationResult != null){
                    setLocation(locationResult.lastLocation.latitude, locationResult.lastLocation.longitude)
                }
            }
        }

        val locationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(3000L)

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallBack, null)

    }
}