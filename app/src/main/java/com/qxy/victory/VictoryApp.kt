package com.qxy.victory

import android.app.Application
import com.qxy.victory.utils.NetWorkUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class VictoryApp : Application() {
  override fun onCreate() {
    super.onCreate()
    NetWorkUtils.instance.init(this)
  }
}
