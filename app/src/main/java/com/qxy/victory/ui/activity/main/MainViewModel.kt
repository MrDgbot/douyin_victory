package com.qxy.victory.ui.activity.main

import com.skydoves.bindables.BindingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor() : BindingViewModel() {

  init {
    Timber.d("init MainViewModel")
//    getResFileLoader()

  }


//  @Throws(IOException::class)
//  fun getResFileLoader(): Properties? {
//    val pro = Properties()
////    java/com/qxy/victory/ui/activity/
//    Log.d("文件测试", javaClass.classLoader.toString())
//    val res: URL = javaClass.classLoader.getResource("MainActivity.class")
//    val path: String = res.getPath()
//    pro.load(FileReader(path))
//    Log.d("文件测试", pro.toString())
//    return pro
//  }

}
