package com.qxy.victory.network

import com.qxy.victory.model.ClintAuthResp
import com.qxy.victory.model.Follower
import com.qxy.victory.model.FollowerResp
import com.qxy.victory.model.RankResp
import com.qxy.victory.model.VideoDetailResp
import com.qxy.victory.model.VideoResp
import com.qxy.victory.utils.Constants
import com.skydoves.sandwich.ApiResponse
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
    openId: String,
    count: Int,
    token: String,
  ): ApiResponse<FollowerResp> =
    dyService.discoveryFollowerList(
      token =  token,
      count = count,
      openId = openId
    )

  suspend fun getFansList(
    openId: String,
    count: Int,
    token: String,
  ): ApiResponse<FollowerResp> =
    dyService.discoveryFansList(
      token =  token,
      count = count,
      openId = openId
    )
}
