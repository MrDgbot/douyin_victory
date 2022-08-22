/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qxy.victory.network

import com.qxy.victory.utils.Constants
import com.qxy.victory.utils.NetWorkUtils
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class HttpRequestInterceptor : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val originalRequest = chain.request()
    val request = originalRequest.newBuilder().url(originalRequest.url)
    // 找到当前类对应Key的文件名
    var currentTokenFileName = ""
    NetWorkUtils.instance.urlClassMap.forEach {
      it.value.forEach { url ->
        if (originalRequest.url.toString().contains(url)) {
          Timber.d("request url : $url")
          currentTokenFileName = it.key
        }
      }
    }
    // 根据文件名获取对应的token
     Timber.d("currentTokenFileName : $currentTokenFileName")
     Timber.d("Code : ${Constants.CODE}")
     Timber.d("AcToken : ${Constants.ACT_TOKEN}")
    when (currentTokenFileName) {
      "OauthUrl" -> {
        Timber.d("OauthUrl")
        request.addHeader("access-token", Constants.CLT_TOKEN).build()
      }
      "AcUrl" -> {
        Timber.d("AcUrl")
        request.addHeader("access-token", Constants.ACT_TOKEN).build()
      }
      else -> {
        request.build()
      }
    }


    return chain.proceed(
      request.build()
    )
  }
}
