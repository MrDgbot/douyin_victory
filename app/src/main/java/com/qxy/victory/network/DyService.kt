package com.qxy.victory.network

import com.qxy.victory.model.ClintAuthResp
import com.qxy.victory.model.MovieResp
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.*

interface DyService {

  @GET("/oauth/client_token/")
  suspend fun oauthClientToken(
    @Query("client_key") client_key: String?,
    @Query("client_secret") client_secret: String,
    @Query("grant_type") grant_type: String
  ): ApiResponse<ClintAuthResp>

  @Headers("Content-Type:application/json")
  @GET("/discovery/ent/rank/item/")
  suspend fun discoveryMovieList(
    @Header("access-token") token: String,
    @Query("type") type: Int
  ): ApiResponse<MovieResp>

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
