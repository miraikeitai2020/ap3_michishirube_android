package com.example.michishirube

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.VISIBILITY_PUBLIC
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavDeepLinkBuilder
import com.example.michishirube.ui.MainActivity
import com.example.michishirube.ui.naviEvaluation.NaviEvaluationFragment

class NotificationService : Service() {
    private var startMode: Int = 0
    private var binder: IBinder? = null
    private var allowRebind: Boolean = false

    override fun onCreate() {
        super.onCreate()
        val id = getString(R.string.channel_id)
        val name = getString(R.string.channel_name)
        val descriptionText = getString(R.string.channel_description)
        val importance = NotificationManager.IMPORTANCE_HIGH
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

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

}
