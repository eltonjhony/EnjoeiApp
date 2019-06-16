package com.eltonjhony.enjoeiapp

import android.app.Application
import com.eltonjhony.enjoeiapp.internal.di.appModules
import com.eltonjhony.enjoeiapp.internal.infrastructure.EnjoeiWorkManager
import org.koin.android.ext.android.startKoin

class EnjoeiApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, appModules)
        EnjoeiWorkManager.init()
    }
}