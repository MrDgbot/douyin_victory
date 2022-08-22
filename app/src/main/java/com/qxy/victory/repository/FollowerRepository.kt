package com.qxy.victory.repository

import androidx.annotation.WorkerThread
import com.qxy.victory.model.FollowerResp
import com.qxy.victory.network.DyClient
import com.qxy.victory.persistence.FollowerDao
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

class FollowerRepository @Inject constructor(
  private val dyClient: DyClient,
  private val followerDao: FollowerDao,
  private val ioDispatcher: CoroutineDispatcher
) : Repository {
  @WorkerThread
  fun fetchFollowerList(
    page: Int,
    type: Int,
    onStart: () -> Unit,
    onComplete: () -> Unit,
    onError: (String?) -> Unit
  ) = flow {
    val requestPage = if (page == -1) 0 else page
    var followerList = followerDao.getList(requestPage, type)

    if (followerList.isEmpty()) {
      lateinit var response: ApiResponse<FollowerResp>
      when (type) {
        1 -> response = dyClient.getFollowerList(requestPage)
        2 -> response = dyClient.getFansList(requestPage)
      }

      response.suspendOnSuccess {
        Timber.d("response2 ${data}")
        if (data.data.list.isNullOrEmpty()) {
          emit(emptyList())
        } else {
          followerList = data.data.list!!
        }
        Timber.d(followerList.toString())
        Timber.d(data.data.list.toString())
        for ((index, rank) in followerList.withIndex()) {
          rank.page = requestPage
          rank.index = index + 1
          rank.type = type
        }
        followerDao.insertList(followerList)
        emit(followerDao.getAllList(requestPage, type))
      }.onFailure {
        Timber.d(message())
        onError(message())

      }.onFailure { // handles the all error cases from the API request fails.
        onError(message())
      }

    } else {
      emit(followerDao.getAllList(requestPage, type))
    }
  }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}