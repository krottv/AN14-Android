package com.example.app.presentation.view

import android.app.Service
import android.content.Intent
import android.os.IBinder
import kotlinx.coroutines.*


class UsbConnectService : Service() {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private val downloadNotification: DownloadNotification = DownloadNotification(this)

    override fun onCreate() {
        super.onCreate()

        coroutineScope.launch {
            startForeground(10, downloadNotification.downloadNotification())
            loadDataFake()
            stopSelf()
            downloadNotification.downloadDoneNotification()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY
    }

    private suspend fun loadDataFake() {
        downloadNotification.createNotificationChannel()

        for (i in 1..100) {
            downloadNotification.processDownloadNotification(i)
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }
}