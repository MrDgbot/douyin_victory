package com.qxy.victory

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bytedance.sdk.open.aweme.authorize.model.Authorization
import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory
import com.bytedance.sdk.open.douyin.DouYinOpenConfig
import com.bytedance.sdk.open.douyin.api.DouYinOpenApi


class MainActivity : AppCompatActivity() {
    lateinit var douYinOpenApi: DouYinOpenApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DouYinOpenApiFactory.init(DouYinOpenConfig("awf5wex6wltac7ft"))
        douYinOpenApi = DouYinOpenApiFactory.create(this)

        val btn1: Button = findViewById(R.id.authButton)
        val textview: TextView = findViewById(R.id.textView)

        btn1.setOnClickListener { textview.text = sendAuth().toString() }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {

        }
    }


    private fun sendAuth(): Boolean {
        val request = Authorization.Request()
        request.scope = "trial.whitelist,following.list" // 用户授权时必选权限
        request.optionalScope0 = "fans.list" // 用户授权时可选权限（默认选择）
        request.state = "ww" // 用于保持请求和回调的状态，授权请求后原样带回给第三方。
        return douYinOpenApi.authorize(request) // 优先使用抖音app进行授权，如果抖音app因版本或者其他原因无法授权，则使用wap页授权
    }
}