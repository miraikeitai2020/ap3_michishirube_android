package com.example.michishirube

import android.Manifest
import android.app.*
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.IBinder
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.VISIBILITY_PUBLIC
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavDeepLinkBuilder
import com.example.michishirube.ui.MainActivity
import com.example.michishirube.ui.naviEvaluation.NaviEvaluationFragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult

class NotificationService : Service() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallBack: LocationCallback

    var myLatitude = 0.0
    var myLongitude = 0.0

    override fun onCreate() {
        super.onCreate()
        fusedLocationClient = FusedLocationProviderClient(applicationContext)


        val pendingIntent: PendingIntent = NavDeepLinkBuilder(applicationContext)
            .setGraph(R.navigation.navigation_graph)
            .setDestination(R.id.naviEvaluationFragment)
            .createPendingIntent()

        startNotification()

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        locationCallBack = object : LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult?) {
                super.onLocationResult(locationResult)
                if(locationResult != null){
                    for(location in locationResult.locations){
                        myLatitude = location.latitude
                        myLongitude = location.longitude
                    }
                }
            }
        }

        val locationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(3000L)

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun stopService(name: Intent?): Boolean {
        return super.stopService(name)
        stopLocationUpdates()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopLocationUpdates()
        stopSelf()
    }

    private fun startLocationUpdates() {
        val locationRequest = LocationRequest.create()?:return
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallBack, null)

    }
    private fun stopLocationUpdates(){
        fusedLocationClient.removeLocationUpdates(LocationCallback())
    }

    private fun startNotification(){
        val id = getString(R.string.channel_id)
        val name = getString(R.string.channel_name)
        val descriptionText = getString(R.string.channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(id, name, importance)
        channel.description = descriptionText

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)


        val pendingIntent: PendingIntent = NavDeepLinkBuilder(applicationContext)
            .setGraph(R.navigation.navigation_graph)
            .setDestination(R.id.naviEvaluationFragment)
            .createPendingIntent()

        val notification = NotificationCompat.Builder(this, id)
            .setContentTitle(getString(R.string.mtg_notification_title_destination))
            .setContentText(getString(R.string.mtg_notification_text_destination))
            .setSmallIcon(R.drawable.icon)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setVisibility(VISIBILITY_PUBLIC)
            .build()

        startForeground(1,notification)

        startLocationUpdates()
    }

//    private fun checkRequestPermission(activity: Activity, context: Context){
//        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
//            val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
//            ActivityCompat.requestPermissions(activity,permissions,1000)
//        }
//    }

}
