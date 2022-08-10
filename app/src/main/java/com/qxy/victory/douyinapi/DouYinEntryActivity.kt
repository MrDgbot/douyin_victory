package com.qxy.victory.douyinapi

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bytedance.sdk.open.aweme.CommonConstants
import com.bytedance.sdk.open.aweme.authorize.model.Authorization
import com.bytedance.sdk.open.aweme.common.handler.IApiEventHandler
import com.bytedance.sdk.open.aweme.common.model.BaseReq
import com.bytedance.sdk.open.aweme.common.model.BaseResp
import com.bytedance.sdk.open.aweme.share.Share
import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory
import com.bytedance.sdk.open.douyin.api.DouYinOpenApi
import com.qxy.victory.MainActivity

class DouYinEntryActivity : AppCompatActivity(), IApiEventHandler {
    lateinit var douYinOpenApi: DouYinOpenApi


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        douYinOpenApi = DouYinOpenApiFactory.create(this)
        douYinOpenApi.handleIntent(intent, this)
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
            val intent: Intent? = null
            Log.d("抖音授权测试", "code：" + response.errorCode + " msg：" + response.errorMsg)

            if (resp.isSuccess()) {
                Toast.makeText(
                    this, "授权成功，获得权限：" + response.grantedPermissions,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onErrorIntent(p0: Intent?) {
        Log.e("抖音授权测试", "onErrorIntent")
        Toast.makeText(this, "Intent出错", Toast.LENGTH_LONG).show()
    }
}