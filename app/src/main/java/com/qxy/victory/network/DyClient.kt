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

  /**
   * 构造Json请求体
   *
   * @param params <字符串，字符串>
   * @return 一个 RequestBody 对象
   */
  private fun createJsonRequestBody(vararg params: Pair<String, String>): RequestBody {
    Timber.d("createJsonRequestBody: ${JSONObject(mapOf(*params)).toString()}")
    return JSONObject(mapOf(*params)).toString()
      .toRequestBody("application/json;".toMediaTypeOrNull())
  }

  /**
   * `oauthClientToken()` 获取网页端授权信息
   */
  suspend fun oauthClientToken(): ApiResponse<ClintAuthResp> =
    dyService.oauthClientToken(
      client_key = Constants.CLIENT_KEY,
      client_secret = Constants.CLIENT_SECRET,
      grant_type = "client_credential"
    )

  /**
   * `oauthAcToken()` 获取客户端授权信息
   */
  suspend fun oauthAcToken(): ApiResponse<ClintAuthResp> =
    dyService.oauthAcToken(
      client_key = Constants.CLIENT_KEY,
      client_secret = Constants.CLIENT_SECRET,
      grant_type = "authorization_code",
      code = Constants.CODE
    )

  /**
   * `oauthUserInfo` 获取用户信息
   */
  suspend fun oauthUserInfo(): ApiResponse<UserInfo> =
    dyService.oauthUserInfo(
      params = createJsonRequestBody(
        "access_token" to Constants.ACT_TOKEN,
        "open_id" to Constants.OPEN_ID
      ),
    )

  /**
   * `discoveryRankList` 榜单列表
   *
   * @param type 1-电影 2-综艺 3-剧集
   * @param token token是用户的登录token，是用户登录后获取的。
   */
  suspend fun discoveryRankList(
    type: Int,
    token: String
  ): ApiResponse<RankResp> =
    dyService.discoveryRankList(
      type = type,
      token = token
    )

  /**
   * `getFollowerList` 查询关注列表
   *
   * @param cursor 光标是下一页的起点。
   */
  suspend fun getFollowerList(
    cursor: Int,
  ): ApiResponse<FollowerResp> =
    dyService.discoveryFollowerList(
      cursor = cursor,
      count = 10,
      openId = Constants.OPEN_ID
    )

  /**
   * `getFansList` 查询粉丝列表
   *
   * @param cursor 光标是查询的起点。第一次查询，光标为0。
   */
  suspend fun getFansList(
    cursor: Int,
  ): ApiResponse<FollowerResp> =
    dyService.discoveryFansList(
      cursor = cursor,
      count = 10,
      openId = Constants.OPEN_ID
    )


  /**
   * `getVideoList` 该接口用于分页获取用户所有视频的数据，返回的数据是实时的。该接口适用于抖音。
   *
   * @param cursor 光标是查询的起点。第一次查询，光标为0。
   */
  suspend fun getVideoList(
    cursor: Int,
  ): ApiResponse<VideoResp> =
    dyService.getVideoList(
      cursor = cursor,
      count = 10,
      openId = Constants.OPEN_ID
    )

  /**
   * `discoveryMockRankList` 模拟榜单API
   *
   * @param type 1：电影，2：综艺，3：剧集
   */
  suspend fun discoveryMockRankList(
    type: Int,
  ): ApiResponse<RankResp> =
    dyService.getMockRank(
      type = type,
      apiId = 33871518
    )

  suspend fun discoveryMockVideoList(
  ): ApiResponse<VideoResp> =
    dyService.getMockVideo()

  suspend fun discoveryMockVideoDetailList(
  ): ApiResponse<VideoDetailResp> =
    dyService.getCurrentVideo()

}
