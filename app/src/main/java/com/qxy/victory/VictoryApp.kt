package com.qxy.victory

import android.app.Application
import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory
import com.bytedance.sdk.open.douyin.DouYinOpenConfig
import com.qxy.victory.utils.Constants
import com.qxy.victory.utils.NetWorkUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class VictoryApp : Application() {
  override fun onCreate() {
    super.onCreate()
    NetWorkUtils.instance.init(this)
    DouYinOpenApiFactory.init(DouYinOpenConfig(Constants.CLIENT_KEY))
  }
}
