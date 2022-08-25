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
  private var cursor: Int = 0

  @WorkerThread
  fun fetchFollowerList(
    page: Int,
    cursor: Int,
    type: Int,
    onStart: () -> Unit,
    onComplete: (Int) -> Unit,
    onError: (String?) -> Unit
  ) = flow {
    val requestPage = if (page == -2) 0 else page
    Timber.d("requestPage$requestPage")
    Timber.d("cursor$cursor")

    var followerList = followerDao.getList(requestPage, type)
    Timber.d(followerList.toString())
    if (followerList.isEmpty()) {
      lateinit var response: ApiResponse<FollowerResp>
      when (type) {
        1 -> response = dyClient.getFollowerList(cursor)
        2 -> response = dyClient.getFansList(cursor)
      }

      response.suspendOnSuccess {
        Timber.d("Page $requestPage And $cursor")
        if (data.data.list.isNullOrEmpty()) {
          Timber.d("responseList${data.data.list.toString()}")

          emit(emptyList())
        } else {
          Timber.d("responseCursor${data.data.cursor}")
          this@FollowerRepository.cursor = if (data.data.cursor == 0) 0 else data.data.cursor!!
          followerList = data.data.list!!
        }
        for ((index, rank) in followerList.withIndex()) {
          rank.page = requestPage
          rank.index = index + 1
          rank.type = type
        }

        followerDao.insertList(followerList)
        emit(followerDao.getAllList(requestPage, type))
        Timber.d(followerList.toString())

      }.onFailure {
        Timber.d(message())
        onError(message())
      }

    } else {
      emit(followerDao.getAllList(requestPage, type))
    }
  }.onStart { onStart() }.onCompletion { onComplete(this@FollowerRepository.cursor) }
    .flowOn(ioDispatcher)
}