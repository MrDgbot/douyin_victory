package com.qxy.victory.repository

import androidx.annotation.WorkerThread
import com.qxy.victory.model.MovieItem
import com.qxy.victory.network.DyClient
import com.qxy.victory.persistence.ShowDao
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject

class ShowRepository @Inject constructor(
  private val dyClient: DyClient,
  private val pokemonDao: ShowDao,
  private val ioDispatcher: CoroutineDispatcher
) : Repository {

  @WorkerThread
  fun fetchShowList(
    page: Int,
    onStart: () -> Unit,
    onComplete: () -> Unit,
    onError: (String?) -> Unit
  ) = flow {
    var pokemons = pokemonDao.getShowList(page)
    if (pokemons.isEmpty()) {
      /**
       * fetches a list of [MovieItem] from the network and getting [ApiResponse] asynchronously.
       * @see [suspendOnSuccess](https://github.com/skydoves/sandwich#apiresponse-extensions-for-coroutines)
       */
      /**
       * fetches a list of [MovieItem] from the network and getting [ApiResponse] asynchronously.
       * @see [suspendOnSuccess](https://github.com/skydoves/sandwich#apiresponse-extensions-for-coroutines)
       */
      val response = dyClient.oauthClientToken()

      response.suspendOnSuccess {
        val token = data.data.accessToken
        val response2 = dyClient.discoveryShowList(3, token)

        response2.suspendOnSuccess {
          pokemons = data.data.list
          Timber.d(data.data.list.toString())
          pokemons.forEach { pokemon -> pokemon.page = page }

          pokemonDao.insertShowList(pokemons)
          emit(pokemonDao.getShowList(page))
        }.onFailure {
          Timber.d(message())
          onError(message())
        }

      }.onFailure { // handles the all error cases from the API request fails.
        onError(message())
      }
    } else {
      emit(pokemonDao.getAllShowList(page))
    }
  }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}
