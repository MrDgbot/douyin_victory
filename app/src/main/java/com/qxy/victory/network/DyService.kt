package com.qxy.victory.network

import com.qxy.victory.model.ClintAuthResp
import com.qxy.victory.model.RankResp
import com.qxy.victory.model.VideoDetailResp
import com.qxy.victory.model.VideoResp
import com.qxy.victory.network.url.OauthUrl
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.*

interface DyService {

  @GET(OauthUrl.token)
  suspend fun oauthClientToken(
    @Query("client_key") client_key: String?,
    @Query("client_secret") client_secret: String,
    @Query("grant_type") grant_type: String
  ): ApiResponse<ClintAuthResp>

  // 影视排行榜
  @GET(OauthUrl.rankItem)
  @Headers("Content-Type:application/json")
  suspend fun discoveryRankList(
    @Header("access-token") token: String,
    @Query("type") type: Int
  ): ApiResponse<RankResp>

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
  suspend fun getVideo(
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
