package com.qxy.victory.repository

import androidx.annotation.WorkerThread
import com.qxy.victory.network.DyClient
import com.qxy.victory.persistence.FollowerDao
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

class FollowerRepository @Inject constructor(
  private val dyClient: DyClient,
  private val followerDao: FollowerDao,
  private val ioDispatcher: CoroutineDispatcher
) : Repository {
  @WorkerThread
  fun fetchFollowerList(
    page: Int,
    onStart: () -> Unit,
    onComplete: () -> Unit,
    onError: (String?) -> Unit
  ) = flow {
    Timber.d("Page ${page}")
    val requestPage = if (page == -1) 0 else page

    var followerList = followerDao.getList(requestPage)
    if (followerList.isEmpty()) {

      var accessToken: String
      var openId: String
      dyClient.oauthClientToken().suspendOnSuccess {
        accessToken = data.data.accessToken
        openId = data.data.openId.toString()

        //val response2 = dyClient.getFollowerList(accessToken,10, openId)
        val response2 = dyClient.getFollowerList(openId,10, accessToken)
        response2.suspendOnSuccess {
          followerList = data.data.list
          Timber.d(followerList.toString())
          Timber.d(data.data.list.toString())
          for ((index, rank) in followerList.withIndex()) {
            rank.page = requestPage
            rank.index = index + 1
          }
          followerDao.insertList(followerList)
          emit(followerDao.getAllList(requestPage))
        }.onFailure {
          Timber.d(message())
          onError(message())

        }.onFailure { // handles the all error cases from the API request fails.
          onError(message())
        }
      }.onFailure {
        Timber.d(message())
        onError(message())
      }

    } else {
      emit(followerDao.getAllList(requestPage))
    }
  }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}