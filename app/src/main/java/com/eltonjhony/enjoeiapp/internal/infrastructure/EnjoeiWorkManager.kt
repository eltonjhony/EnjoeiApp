package com.eltonjhony.enjoeiapp.internal.infrastructure

import android.content.Context
import android.content.Intent
import androidx.work.*
import com.eltonjhony.enjoeiapp.R
import com.eltonjhony.enjoeiapp.presentation.home.HomeActivity
import java.util.concurrent.TimeUnit

class EnjoeiWorkManager(val context: Context, params: WorkerParameters) : Worker(context, params) {

    companion object {
        private val TAG = EnjoeiWorkManager::class.java.simpleName

        fun init() {

            val workManager = WorkManager.getInstance()

            val constraints: Constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            workManager.cancelAllWorkByTag(TAG)

            val periodicBuilder =
                PeriodicWorkRequest.Builder(EnjoeiWorkManager::class.java, 24, TimeUnit.HOURS)
            val worker = periodicBuilder.addTag(TAG).setConstraints(constraints).build()
            workManager.enqueueUniquePeriodicWork(TAG, ExistingPeriodicWorkPolicy.KEEP, worker)
        }

    }

    override fun doWork(): Result {
        NotificationManager(context = context).sendNotification(
            context.getString(R.string.notification_title), context.getString(
                R.string.notification_message
            ), Intent(context, HomeActivity::class.java)
        )
        return Result.success()
    }

}