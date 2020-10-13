package com.example.michishirube.ui

import DestinationListQuery
import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toDeferred
import com.apollographql.apollo.exception.ApolloException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

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

    //spot何たらはgraphQlから値をとってくる
//    var spotName: String? = ""
    var spotName: MutableLiveData<String> = MutableLiveData<String>("読み込み中")

    //目的地の緯度経度（とりあえず今は未来大が入っている）
    var spotLatitude: Double? = 41.841714
    var spotLongitude: Double? = 140.766817

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

    fun setDeviceLocation(latitude: Double?, longitude: Double?) {
        if(latitude != null && longitude != null){
            deviceLatitude = latitude
            deviceLongitude = longitude
        }
    }

    fun loadDestination(context: Context){//もしかしたらここら辺はちゃんとそれらの（？）ViewModelで書くかも
        //Coroutinesを使用して，Repositryの関数を使って，目的地名を持ってくる
        //withContextでここの目的地名のテキスト（spotName）に値入れて，Fragmentの方でFragmentの方の目的地名（レイアウトと直結してる方）に値追加かな
        //上にプラスでgraphQLから値を撮ってきてspotLatitude,spotLongitudeに目的地の緯度経度をいれる
        CoroutineScope(Dispatchers.IO).launch {
            val res = try {
                apolloClient(context).query(
                    DestinationListQuery(
                        deviceLatitude = deviceLatitude,
                        deviceLongitude = deviceLongitude,
                        worktime = sumMinute,
                        emotion = emotion
                    )
                ).toDeferred().await()
            }catch (ex: ApolloException){
                Log.e("checker",ex.toString())
                return@launch
            }
            val destinationsName = res?.data?.spots?.spots?.map{ it?.name }?:return@launch
            val destinationsLatitude = res?.data?.spots?.spots?.map{ it?.locate?.latitude }?:return@launch
            val destinationsLongitude = res?.data?.spots?.spots?.map{ it?.locate?.longitude }?:return@launch

            withContext(Dispatchers.Main) {
                val spotRandom = (0..destinationsName.size - 1).shuffled().first()
                spotName.postValue(destinationsName[spotRandom])
                spotLatitude = destinationsLatitude[spotRandom]
                spotLongitude = destinationsLongitude[spotRandom]

            }
        }
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
                setDeviceLocation(location.latitude, location.longitude)
            }else{
                //位置情報の取得に失敗しました，的な何かを出したい
                //UIに関わる部分なのでiOSやデザインと話し合いが必要だと思ったので保留
            }
        }

        locationCallBack = object : LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult?) {
                super.onLocationResult(locationResult)
                if(locationResult != null){
                    setDeviceLocation(locationResult.lastLocation.latitude, locationResult.lastLocation.longitude)
                }
            }
        }

        val locationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(3000L)

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallBack, null)

    }


    fun startGPS(activity: Activity){
        val locationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(3000L)

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)

        val client: SettingsClient = LocationServices.getSettingsClient(activity)

        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())


        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException){
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    exception.startResolutionForResult(activity,0x1)
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }

    //apollo
    private fun apolloClient(context: Context): ApolloClient {
        return ApolloClient.builder()
            .serverUrl("https://miraikeitai2020-bff.herokuapp.com/query")
            .okHttpClient (
                OkHttpClient.Builder()
                    .addInterceptor(AuthorizationInterceptor(context))
                    .build()
            )
            .build()
    }

    private class AuthorizationInterceptor(val context: Context): Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
//                .addHeader("id","nanairoaisu")
//                .addHeader("pass","nanachan")
                .addHeader("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOiIyMDIwLTEwLTEyVDE3OjA1OjAyLjY1MDA4NDY4M1oiLCJzdWIiOiJuYW5haXNvYWlzdSJ9.Cjp90e5PJmWbqWBwXDAf2HNYdvSwEb69INNggX0tOHg")
                .build()

            return chain.proceed(request)
        }
    }

}