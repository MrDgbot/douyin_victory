package com.qxy.victory.network

import com.qxy.victory.model.ClintAuthResp
import com.qxy.victory.model.MovieResp
import com.qxy.victory.model.SeriesResp
import com.qxy.victory.model.ShowResp
import com.qxy.victory.utils.Constants
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class DyClient @Inject constructor(
  private val dyService: DyService
) {

  suspend fun oauthClientToken(): ApiResponse<ClintAuthResp> =
    dyService.oauthClientToken(
      client_key = Constants.CLIENT_KEY,
      client_secret = Constants.CLIENT_SECRET,
      grant_type = "client_credential"
    )


  suspend fun discoveryMovieList(
    type: Int,
    token: String
  ): ApiResponse<MovieResp> =
    dyService.discoveryMovieList(
      type = type,
      token = token
    )

  suspend fun discoverySeriesList(
    type: Int,
    token: String
  ): ApiResponse<SeriesResp> =
    dyService.discoverySeriesList(
      type = type,
      token = token
    )

  suspend fun getSeriesMockDta(
  ): ApiResponse<SeriesResp> = dyService.getMockSeriesData()


  suspend fun discoveryShowList(
    type: Int,
    token: String
  ): ApiResponse<ShowResp> =
    dyService.discoveryShowList(
      type = type,
      token = token
    )

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
