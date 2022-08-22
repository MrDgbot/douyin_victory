package com.qxy.victory.network

import com.qxy.victory.model.ClintAuthResp
import com.qxy.victory.model.*
import com.qxy.victory.utils.Constants
import com.skydoves.sandwich.ApiResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import timber.log.Timber
import javax.inject.Inject

class DyClient @Inject constructor(
  private val dyService: DyService
) {
  // 请求网页授权
  suspend fun oauthClientToken(): ApiResponse<ClintAuthResp> =
    dyService.oauthClientToken(
      client_key = Constants.CLIENT_KEY,
      client_secret = Constants.CLIENT_SECRET,
      grant_type = "client_credential"
    )

  // 请求客户端授权
  suspend fun oauthAcToken(): ApiResponse<ClintAuthResp> =
    dyService.oauthAcToken(
      client_key = Constants.CLIENT_KEY,
      client_secret = Constants.CLIENT_SECRET,
      grant_type = "authorization_code",
      code = Constants.CODE
    )

  // 获取用户公开信息
  suspend fun oauthUserInfo(): ApiResponse<UserInfo> =
    dyService.oauthUserInfo(
      params = createJsonRequestBody(
        "access_token" to Constants.ACT_TOKEN,
        "open_id" to Constants.OPEN_ID
      ),
    )

  suspend fun discoveryRankList(
    type: Int,
    token: String
  ): ApiResponse<RankResp> =
    dyService.discoveryRankList(
      type = type,
      token = token
    )

  suspend fun discoveryMockRankList(
    type: Int,
  ): ApiResponse<RankResp> =
    dyService.getMockRank(
      type = type,
      apiId = 33871518
    )

  suspend fun discoveryMockVideoList(

  ): ApiResponse<VideoResp> =
    dyService.getVideo(
    )

  suspend fun discoveryMockVideoDetailList(

  ): ApiResponse<VideoDetailResp> =
    dyService.getCurrentVideo(
    )


  suspend fun getFollowerList(
    count: Int,
  ): ApiResponse<FollowerResp> =
    dyService.discoveryFollowerList(
      count = count,
      openId = Constants.OPEN_ID
    )

  suspend fun getFansList(
    openId: String,
    count: Int,
    token: String,
  ): ApiResponse<FollowerResp> =
    dyService.discoveryFansList(
      count = count,
      openId = openId
    )

  private fun createJsonRequestBody(vararg params: Pair<String, String>): RequestBody {
    Timber.d("createJsonRequestBody: ${JSONObject(mapOf(*params)).toString()}")
    return JSONObject(mapOf(*params)).toString()
      .toRequestBody("application/json;".toMediaTypeOrNull())
  }
}
