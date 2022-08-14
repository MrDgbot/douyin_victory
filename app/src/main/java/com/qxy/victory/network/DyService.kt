package com.qxy.victory.network

import com.qxy.victory.model.ClintAuthResp
import com.qxy.victory.model.MovieResp
import com.qxy.victory.model.SeriesResp
import com.qxy.victory.model.ShowResp
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

  @GET(OauthUrl.rankItem)
  @Headers("Content-Type:application/json")
  suspend fun discoveryMovieList(
    @Header("access-token") token: String,
    @Query("type") type: Int
  ): ApiResponse<MovieResp>

  @Headers("Content-Type:application/json")
  @GET("/discovery/ent/rank/item/")//api/getShow
  //@GET("api/getShow")
  suspend fun discoverySeriesList(
    @Header("access-token") token: String,
    @Query("type") type: Int
  ): ApiResponse<SeriesResp>

  @Headers("Content-Type:application/json")
  @GET("/discovery/ent/rank/item/")//api/getShow
  //@GET("api/getShow")
  suspend fun discoveryShowList(
    @Header("access-token") token: String,
    @Query("type") type: Int
  ): ApiResponse<ShowResp>

//
//  @GET("pokemon")
//  suspend fun fetchPokemonList(
//    @Query("limit") limit: Int = 20,
//    @Query("offset") offset: Int = 0
//  ): ApiResponse<PokemonResponse>
//
//  @GET("pokemon/{name}")
//  suspend fun fetchPokemonInfo(@Path("name") name: String): ApiResponse<PokemonInfo>
}
