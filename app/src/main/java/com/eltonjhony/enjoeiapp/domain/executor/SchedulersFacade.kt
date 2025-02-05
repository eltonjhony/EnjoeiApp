package com.eltonjhony.enjoeiapp.domain.executor

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulersFacade {

    fun io() : Scheduler {
        return Schedulers.io()
    }

    fun ui() : Scheduler {
        return AndroidSchedulers.mainThread()
    }
}