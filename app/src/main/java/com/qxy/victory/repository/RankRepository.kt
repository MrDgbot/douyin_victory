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
    Timber.d("Page ${page}")
    val requestPage = if (page == -1) 0 else page

    var rankList = rankDao.getList(requestPage, rankType)
    if (rankList.isEmpty()) {

      //val response2 = dyClient.discoveryRankList(rankType, data.data.accessToken)
      val response2 = dyClient.discoveryMockRankList(rankType)

      response2.suspendOnSuccess {
        rankList = data.data.list
        Timber.d(rankList.toString())
        Timber.d(data.data.list.toString())
        for ((index, rank) in rankList.withIndex()) {
          rank.page = requestPage
          rank.index = index + 1
        }
        rankDao.insertList(rankList)
        emit(rankDao.getAllList(requestPage, rankType))
      }.onFailure {
        Timber.d(message())
        onError(message())

      }.onFailure { // handles the all error cases from the API request fails.
        onError(message())
      }
    } else {
      emit(rankDao.getAllList(requestPage, rankType))
    }
  }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)

}
