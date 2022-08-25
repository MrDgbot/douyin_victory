package com.qxy.victory.ui.fragment.mine

import android.os.Bundle
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import com.bytedance.sdk.open.aweme.authorize.model.Authorization
import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory
import com.bytedance.sdk.open.douyin.api.DouYinOpenApi
import com.qxy.victory.R
import com.qxy.victory.databinding.FragmentMineBinding
import com.qxy.victory.ui.adapter.MineAdapter
import com.qxy.victory.utils.Constants
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MineFragment : BindingFragment<FragmentMineBinding>(R.layout.fragment_mine) {

  @get:VisibleForTesting
  internal val viewModel: MineViewModel by viewModels()

  lateinit var douyinopenapi: DouYinOpenApi
  lateinit var minieFragmentActivity: FragmentActivity

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    minieFragmentActivity = this.requireActivity()
    douyinopenapi = DouYinOpenApiFactory.create(this.activity)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding {
      vm = viewModel
      adapter = MineAdapter(minieFragmentActivity, 4)
    }
  }

  override fun onResume() {
    super.onStart()
    if (Constants.CODE.isEmpty()) {
      sendAuth()
    } else {
      Timber.d("onResume: ${Constants.CODE}")
      viewModel.refreshList()
    }

  }

  fun sendAuth() {
    val request = Authorization.Request()
//    private val mScope = "user_info,video.list,video.data,fans.list,following.list,"

    request.scope = Constants.AUTH_PERMISSION // 用户授权时必选权限
//    request.optionalScope0 = "mobile" // 用户授权时可选权限（默认选择）
    request.state = "ww" // 用于保持请求和回调的状态，授权请求后原样带回给第三方。
    request.callerLocalEntry = "com.qxy.victory.ui.activity.auth.AuthActivity";

    douyinopenapi.authorize(request) // 优先使用抖音app进行授权，如果抖音app因版本或者其他原因无法授权，则使用wap页授权
  }
}