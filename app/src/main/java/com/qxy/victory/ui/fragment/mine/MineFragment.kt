package com.qxy.victory.ui.fragment.mine

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import com.bytedance.sdk.open.aweme.authorize.model.Authorization
import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory
import com.bytedance.sdk.open.douyin.api.DouYinOpenApi
import com.qxy.victory.R
import com.qxy.victory.databinding.FragmentMineBinding
import com.qxy.victory.network.douyinapi.DouYinEntryActivity
import com.qxy.victory.utils.Constants
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MineFragment : BindingFragment<FragmentMineBinding>(R.layout.fragment_mine) {

  @get:VisibleForTesting
  internal val viewModel: MineViewModel by viewModels()

  lateinit var douyinopenapi:DouYinOpenApi

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    douyinopenapi=DouYinOpenApiFactory.create(this.activity)
    //Log.d("MineFragment", "onCreate: ")
    if(Constants.ACT_TOKEN.equals("")){
      sendAuth()
    }
  }


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding {
      vm = viewModel
    }
  }

  private val mScope="user_info,video.list,video.data,fans.list,following.list"
  fun sendAuth(){
    Timber.d("拉起授权")
    val request = Authorization.Request()
    request.scope = mScope // 用户授权时必选权限
    request.optionalScope0 = "mobile" // 用户授权时可选权限（默认选择）
    //request.optionalScope0 = mOptionalScope1;    // 用户授权时可选权限（默认不选）
    //request.optionalScope0 = mOptionalScope1;    // 用户授权时可选权限（默认不选）
    request.state = "ww" // 用于保持请求和回调的状态，授权请求后原样带回给第三方。
    douyinopenapi.authorize(request) // 优先使用抖音app进行授权，如果抖音app因版本或者其他原因无法授权，则使用wap页授权
  }
}