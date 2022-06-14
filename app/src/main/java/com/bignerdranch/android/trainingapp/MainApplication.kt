package com.bignerdranch.android.trainingapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager


class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val workerChannel = NotificationChannel(
            Constants.WORKER_CHANNEL_ID,
            Constants.WORKER_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )

        val notificationManager = getSystemService(NotificationManager::class.java)

        notificationManager.createNotificationChannel(workerChannel)
    }
}