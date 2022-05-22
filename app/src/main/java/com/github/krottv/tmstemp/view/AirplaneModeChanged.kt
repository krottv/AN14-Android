package com.github.krottv.tmstemp.view

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.github.krottv.tmstemp.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private const val CHANNEL_ID = "message"

class AirplaneModeChanged : Service() {

    override fun onCreate() {
        super.onCreate()

        runBlocking {
            launch {
                startForeground(10, loadNotification())
                loadDataFake()
                stopSelf()
                completeNotification()
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY
    }

    private suspend fun loadDataFake() {
        createNotificationChannel()

        for (i in 1..50) {

            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Loading")

                .setProgress(50, i, false)
                .setPriority(NotificationCompat.PRIORITY_HIGH)

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(10, builder.build())
            delay(100)
        }
    }

    private fun loadNotification(): Notification {
        createNotificationChannel()

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Loading")
            .setContentText("Foreground service")
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        return builder.build()
    }

    private fun completeNotification() {
        createNotificationChannel()

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Loading")
            .setContentText("is complete")
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(2, builder.build())
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Localized Name"
            val descriptionText = "Localized Description"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}