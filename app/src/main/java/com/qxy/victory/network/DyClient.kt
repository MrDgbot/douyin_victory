package com.qxy.victory.network

import com.qxy.victory.model.ClintAuthResp
import com.qxy.victory.model.RankResp
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

  suspend fun discoveryRankList(
    type: Int,
    token: String
  ): ApiResponse<RankResp> =
    dyService.discoveryRankList(
      type = type,
      token = token
    )

  suspend fun getSeriesMockDta(
  ): ApiResponse<RankResp> = dyService.getMockSeriesData()

  //获取电影榜
  suspend fun getMovieMockData(
  ):ApiResponse<RankResp> = dyService.getMockMovieData();

  //获取电影榜
  suspend fun getTvMockData(
  ):ApiResponse<RankResp> = dyService.getMockTvData();

//  suspend fun fetchPokemonList(
//    page: Int
//  ): ApiResponse<PokemonResponse> =
//    pokedexService.fetchPokemonList(
//      limit = PAGING_SIZE,
//      offset = page * PAGING_SIZE
//    )
//
//  suspend fun fetchPokemonInfo(
//    name: String
//  ): ApiResponse<PokemonInfo> =
//    pokedexService.fetchPokemonInfo(
//      name = name
//    )
//
//  companion object {
//    private const val PAGING_SIZE = 20
//  }
}
