package com.qxy.victory.repository

import androidx.annotation.WorkerThread
import com.qxy.victory.network.DyClient
import com.qxy.victory.persistence.SeriesDao
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

class SeriesRepository @Inject constructor(
  private val dyClient: DyClient,
  private val pokemonDao: SeriesDao,
  private val ioDispatcher: CoroutineDispatcher
  ) : Repository {

    @WorkerThread
    fun fetchSeriesList(
      page: Int,
      onStart: () -> Unit,
      onComplete: () -> Unit,
      onError: (String?) -> Unit
    ) = flow {
      var seriesList = pokemonDao.getSeriesList(page)
      if (seriesList.isEmpty()) {

        val response = dyClient.oauthClientToken()

        response.suspendOnSuccess {
          val token = data.data.accessToken
          val response2 = dyClient.discoverySeriesList(2, token)

          response2.suspendOnSuccess {
            seriesList = data.data.list
            Timber.d(data.data.list.toString())
            seriesList.forEach { pokemon -> pokemon.page = page }

            pokemonDao.insertSeriesList(seriesList)
            emit(pokemonDao.getAllSeriesList(page))
          }.onFailure {
            Timber.d(message())
            onError(message())
          }

        }.onFailure { // handles the all error cases from the API request fails.
          onError(message())
        }
      } else {
        emit(pokemonDao.getAllSeriesList(page))
      }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}