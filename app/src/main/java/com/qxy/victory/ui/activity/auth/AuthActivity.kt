package com.qxy.victory.ui.activity.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.bytedance.sdk.open.aweme.CommonConstants
import com.bytedance.sdk.open.aweme.authorize.model.Authorization
import com.bytedance.sdk.open.aweme.common.handler.IApiEventHandler
import com.bytedance.sdk.open.aweme.common.model.BaseReq
import com.bytedance.sdk.open.aweme.common.model.BaseResp
import com.bytedance.sdk.open.aweme.share.Share
import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory
import com.bytedance.sdk.open.douyin.api.DouYinOpenApi
import com.qxy.victory.R
import com.qxy.victory.databinding.ActivityAuthBinding
import com.qxy.victory.ui.activity.main.MainActivity
import com.qxy.victory.utils.Constants
import com.skydoves.bindables.BindingActivity
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AuthActivity : BindingActivity<ActivityAuthBinding>(R.layout.activity_auth),
  IApiEventHandler {

  private lateinit var douYinOpenApi: DouYinOpenApi
  private lateinit var tips: TextView
  @get:VisibleForTesting
  internal val viewModel: AuthViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    onTransformationStartContainer()
    super.onCreate(savedInstanceState)
    binding {
      vm = viewModel
    }
    douYinOpenApi = DouYinOpenApiFactory.create(this)
    douYinOpenApi.handleIntent(intent, this)
//    myBinding = ActivityAuthBinding.inflate(layoutInflater)

  }

  override fun onReq(p0: BaseReq?) {
    TODO("Not yet implemented")
  }

  override fun onResp(resp: BaseResp?) {
    if (resp == null) return

    if (resp.type == CommonConstants.ModeType.SHARE_CONTENT_TO_TT_RESP) {
      val response = resp as Share.Response
      Toast.makeText(
        this,
        " code：" + response.errorCode + " 文案：" + response.errorMsg,
        Toast.LENGTH_SHORT
      ).show()

      val intent = Intent(this, MainActivity::class.java)
      startActivity(intent)
    } else if (resp.type == CommonConstants.ModeType.SEND_AUTH_RESPONSE) {
      val response = resp as Authorization.Response

      if (resp.isSuccess()) {
        Toast.makeText(this,"授权中，请等待！",Toast.LENGTH_SHORT).show()
        Constants.CODE = response.authCode
        viewModel.fetchNextPokemonList()
        Log.d("抖音授权测试", "authcode：" + response.authCode)
      }
    }
  }

  override fun onErrorIntent(p0: Intent?) {
//    myBinding.authTips.text = "Intent出错啦！"
    Toast.makeText(this, "Intent出错啦", Toast.LENGTH_LONG).show()
  }
}
