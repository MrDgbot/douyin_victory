package com.qxy.victory.ui.activity.videoplay

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.*
import androidx.annotation.VisibleForTesting
import com.qxy.victory.R
import com.qxy.victory.databinding.ActivityVideoPlayBinding
import com.qxy.victory.model.VideoData
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.bundleNonNull
import com.skydoves.bundler.intentOf
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationLayout
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class VideoPlayActivity : BindingActivity<ActivityVideoPlayBinding>(R.layout.activity_video_play) {

  @get:VisibleForTesting
  private val  videoData: VideoData by bundleNonNull(EXTRA_POKEMON)

  lateinit var activityVideoPlayBinding:ActivityVideoPlayBinding

//  @SuppressLint("SetJavaScriptEnabled")
  override fun onCreate(savedInstanceState: Bundle?) {
    onTransformationStartContainer()
    super.onCreate(savedInstanceState)

    activityVideoPlayBinding= ActivityVideoPlayBinding.inflate(layoutInflater)

    init()

    //Timber.d("测试11："+videoData)
//    binding {
//      item=videoData
//    }
    setContentView(activityVideoPlayBinding.root)
    activityVideoPlayBinding.item=videoData
  }

  fun init(){
    val webView = activityVideoPlayBinding.videoWebView
    webView.webViewClient = object : WebViewClient() {
      override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        if (url != null) {
          view?.loadUrl(url)
        }
        return true
      }

      override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
      }

      override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
      }
    }

    //activityVideoPlayBinding.ceshiText.text="666"



    var webSettings = webView!!.settings
    webSettings.javaScriptEnabled = true  // 开启 JavaScript 交互
    webSettings.setAppCacheEnabled(true) // 启用或禁用缓存
    webSettings.cacheMode = WebSettings.LOAD_DEFAULT // 只要缓存可用就加载缓存, 哪怕已经过期失效 如果缓存不可用就从网络上加载数据
    webSettings.setAppCachePath(cacheDir.path) // 设置应用缓存路径

    // 缩放操作
    webSettings.setSupportZoom(false) // 支持缩放 默认为true 是下面那个的前提
    webSettings.builtInZoomControls = false // 设置内置的缩放控件 若为false 则该WebView不可缩放
    webSettings.displayZoomControls = false // 隐藏原生的缩放控件

    webSettings.blockNetworkImage = false // 禁止或允许WebView从网络上加载图片
    webSettings.loadsImagesAutomatically = true // 支持自动加载图片

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      webSettings.safeBrowsingEnabled = true // 是否开启安全模式
    }

    webSettings.javaScriptCanOpenWindowsAutomatically = true // 支持通过JS打开新窗口
    webSettings.domStorageEnabled = true // 启用或禁用DOM缓存
    webSettings.setSupportMultipleWindows(true) // 设置WebView是否支持多窗口

    // 设置自适应屏幕, 两者合用
    webSettings.useWideViewPort = true  // 将图片调整到适合webview的大小
    webSettings.loadWithOverviewMode = true  // 缩放至屏幕的大小
    webSettings.allowFileAccess = true // 设置可以访问文件

    webSettings.setGeolocationEnabled(true) // 是否使用地理位置

    webView?.fitsSystemWindows = true
    webView?.setLayerType(View.LAYER_TYPE_HARDWARE,null)
  }

  override fun onStart() {
    super.onStart()
    activityVideoPlayBinding.videoWebView.loadUrl(videoData.shareUrl)
  }

  companion object {
    internal const val EXTRA_POKEMON = "EXTRA_POKEMON"
    fun startActivity(transformationLayout: TransformationLayout, newVideoData: VideoData) =
      transformationLayout.context.intentOf<VideoPlayActivity> {
        Timber.d("测试："+newVideoData)
        putExtra(EXTRA_POKEMON to newVideoData)
        TransformationCompat.startActivity(transformationLayout, intent)
      }
  }
}