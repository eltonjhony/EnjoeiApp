package com.eltonjhony.enjoeiapp.internal.infrastructure

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.eltonjhony.enjoeiapp.R

class NotificationManager(private val context: Context) {

    init {
        createNotificationChannel()
    }

    fun sendNotification(title: String, message: String, resultIntent: Intent) {
        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(resultIntent)
            getPendingIntent(100, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        NotificationCompat.Builder(context, CHANNEL_ID).apply {
            setContentIntent(resultPendingIntent)
            setSmallIcon(R.mipmap.ic_enjoei)
            setContentTitle(title)
            setContentText(message)
            priority = NotificationCompat.PRIORITY_HIGH
        }.also {
            with(NotificationManagerCompat.from(context)) {
                notify(12, it.build())
            }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        private const val CHANNEL_ID = "channelId"
    }

}