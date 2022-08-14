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

package com.qxy.victory.repository

import androidx.annotation.WorkerThread
import com.qxy.victory.network.DyClient
import com.qxy.victory.persistence.PokemonDao
import com.skydoves.sandwich.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject

class MovieRepository @Inject constructor(
  private val dyClient: DyClient,
  private val pokemonDao: PokemonDao,
  private val ioDispatcher: CoroutineDispatcher
) : Repository {

  @WorkerThread
  fun fetchMovieList(
    page: Int,
    onStart: () -> Unit,
    onComplete: () -> Unit,
    onError: (String?) -> Unit
  ) = flow {
    var moviesList = pokemonDao.getPokemonList(page)
    if (moviesList.isEmpty()) {

      val response = dyClient.oauthClientToken()

      response.suspendOnSuccess {
        val token = data.data.accessToken
        //替代为Mock
        val response2=dyClient.getMovieMockData();
        //val response2 = dyClient.discoveryMovieList(1, token)

        response2.suspendOnSuccess {
          moviesList = data.data.list
          Timber.d(data.data.list.toString())
          //moviesList.forEach { pokemon -> pokemon.page = page }

          for((index,pokemon) in moviesList.withIndex()){
            pokemon.page=page
            pokemon.index=index+1;
          }
          pokemonDao.insertPokemonList(moviesList)
          emit(pokemonDao.getAllPokemonList(page))
        }.onFailure {
          Timber.d(message())
          onError(message())
        }

      }.onFailure { // handles the all error cases from the API request fails.
        onError(message())
      }
    } else {
      emit(pokemonDao.getAllPokemonList(page))
    }
  }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}
