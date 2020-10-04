package com.example.michishirube.ui

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult

class NavigationSharedViewModel: ViewModel() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallBack: LocationCallback

    //emotionSelect
    var emotion = 0

    //timeSelect
    var hour = 0
    var minute = 10
    var sumMinute = 0//所要時間

    //naviDestination
    var deviceLatitude = 0.0
    var deviceLongitude = 0.0
    var spotName = ""

    //目的地の緯度経度（とりあえず今は未来大が入っている）
    var spotLatitude = 41.841714
    var spotLongitude = 140.766817


    //emotionSelect
    fun setEmotionType(selectedEmotion: Int){
        emotion = selectedEmotion
    }

    //timeSelect
    fun setTime(selectedHour:Int, selectedMinute:Int){
        hour = selectedHour
        minute = selectedMinute
        sumMinute = hour * 60 + minute
    }

    fun setLocation(latitude: Double?, longitude: Double?) {
        if(latitude != null && longitude != null){
            deviceLatitude = latitude
            deviceLongitude = longitude
        }

    }

    fun loadDestination(){//もしかしたらここら辺はちゃんとそれらの（？）ViewModelで書くかも
        //Coroutinesを使用して，Repositryの関数を使って，目的地名を持ってくる
        //withContextでここの目的地名のテキスト（spotName）に値入れて，Fragmentの方でFragmentの方の目的地名（レイアウトと直結してる方）に値追加かな
        //上にプラスでgraphQLから値を撮ってきてspotLatitude,spotLongitudeに目的地の突っ込む緯度経度
    }

    //naviDestination
    fun intentDestination(): Intent {
        val uriStr = "https://www.google.com/maps/dir/?api=1&destination=${spotLatitude},${spotLongitude}"
        val uri = Uri.parse(uriStr)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        return intent
    }

    //naviEvaluation 2ndスプリント
    fun postEvaluate(){//もしかしたらここら辺はちゃんとそれらの（？）ViewModelで書くかも
        //たぶんここでコルーチン
    }

    //位置情報取得
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