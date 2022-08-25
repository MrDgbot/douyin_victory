package com.qxy.victory.network

import com.qxy.victory.model.*
import com.qxy.victory.network.url.AcUrl
import com.qxy.victory.network.url.OauthUrl
import com.skydoves.sandwich.ApiResponse
import okhttp3.RequestBody
import retrofit2.http.*

interface DyService {
  // 网页Token
  @GET(OauthUrl.token)
  suspend fun oauthClientToken(
    @Query("client_key") client_key: String?,
    @Query("client_secret") client_secret: String,
    @Query("grant_type") grant_type: String
  ): ApiResponse<ClintAuthResp>

  // 客户端Token
  @GET(AcUrl.token)
  suspend fun oauthAcToken(
    @Query("client_key") client_key: String?,
    @Query("client_secret") client_secret: String,
    @Query("grant_type") grant_type: String,
    @Query("code") code: String
  ): ApiResponse<ClintAuthResp>

  // 获取用户公开信息
  @POST(AcUrl.userInfo)
  @Headers("Content-Type:application/json")
  suspend fun oauthUserInfo(
    @Body params: RequestBody,
  ): ApiResponse<UserInfo>

  //用户关注的人
  @GET(AcUrl.following)
  @Headers("Content-Type:application/json")
  suspend fun discoveryFollowerList(
    @Query("open_id") openId: String,
    @Query("count") count: Int,
    @Query("cursor") cursor: Int,
  ): ApiResponse<FollowerResp>

  //粉丝
  //用户关注的人
  @GET(AcUrl.fans)
  @Headers("Content-Type:application/json")
  suspend fun discoveryFansList(
    @Query("open_id") openId: String,
    @Query("count") count: Int,
    @Query("cursor") cursor: Int,
  ): ApiResponse<FollowerResp>

  // 影视排行榜
  @GET(OauthUrl.rankItem)
  @Headers("Content-Type:application/json")
  suspend fun discoveryRankList(
    @Header("access-token") token: String,
    @Query("type") type: Int
  ): ApiResponse<RankResp>

  /// 查询授权账号视频列表
  @Headers("Content-Type:application/json")
  @GET(AcUrl.videoList)
  suspend fun getVideoList(
    @Query("open_id") openId: String,
    @Query("count") count: Int,
    @Query("cursor") cursor: Int,
  ): ApiResponse<VideoResp>


  /// Mock影视排行榜
  @Headers("Content-Type:application/json")
  @GET("https://mock.apifox.cn/m1/1435365-0-default/discovery/ent/rank/item/")
  suspend fun getMockRank(
    @Query("apifoxApiId") apiId: Int,
    @Query("type") type: Int
  ): ApiResponse<RankResp>



  /// Mock用户具体的视频
  @Headers("Content-Type:application/json")
  @GET("https://www.fastmock.site/mock/26ad7e30cf27a9e2cc7477c13b148794/api/api/getVideo/")
  suspend fun getMockVideo(
//    @Query("open_id") openId: String,
//    @Query("count") count: Int,
  ): ApiResponse<VideoResp>

  /// Mock用户具体的视频
  @Headers("Content-Type:application/json")
  @FormUrlEncoded
  @POST("api/postCurrentVideo")
  suspend fun getCurrentVideo(
//    @Field("item_ids") itemIds:String
  ): ApiResponse<VideoDetailResp>

}
