package com.qxy.victory

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class VictoryApp : Application(){
    override fun onCreate() {
        super.onCreate()
        ARouter.init(this)
    }
}
