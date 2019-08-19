package com.upadhyay.exoplayertest

import android.app.Activity
import android.app.Application
import android.util.Log
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MainApplication : Application(), HasActivityInjector {


    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
//        Log.d(MainApplication::class.simpleName, "Application is been created")
//        AppInjector.init(this)
    }

    override fun activityInjector() = activityDispatchingAndroidInjector

}