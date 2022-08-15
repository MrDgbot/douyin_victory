package com.qxy.victory.repository

import androidx.annotation.WorkerThread
import com.qxy.victory.network.DyClient
import com.qxy.victory.persistence.RankDao
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

class RankRepository @Inject constructor(
  private val dyClient: DyClient,
  private val rankDao: RankDao,
  private val ioDispatcher: CoroutineDispatcher
) : Repository {

  @WorkerThread
  fun fetchRankList(
    page: Int,
    rankType: Int,
    onStart: () -> Unit,
    onComplete: () -> Unit,
    onError: (String?) -> Unit
  ) = flow {
    var rankList = rankDao.getList(page, rankType)
    if (rankList.isEmpty()) {
      val response = dyClient.oauthClientToken()
      response.suspendOnSuccess {
        //val response2 = dyClient.discoveryRankList(rankType, data.data.accessToken)
        var response2 = dyClient.getSeriesMockDta()
        when (rankType) {
          1 -> response2 = dyClient.getMovieMockData()
          2 -> response2 = dyClient.getSeriesMockDta()
          3 -> response2 = dyClient.getTvMockData()
        }

        response2.suspendOnSuccess {
          rankList = data.data.list
          Timber.d(data.data.list.toString())
          for ((index, rank) in rankList.withIndex()) {
            rank.page = page
            rank.index = index + 1
          }

          rankDao.insertList(rankList)
          emit(rankDao.getAllList(page, rankType))
        }.onFailure {
          Timber.d(message())
          onError(message())
        }

      }.onFailure { // handles the all error cases from the API request fails.
        onError(message())
      }
    } else {
      emit(rankDao.getAllList(page, rankType))
    }
  }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)

}
