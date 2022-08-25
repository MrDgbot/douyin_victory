package com.qxy.victory.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.qxy.victory.network.DyClient
import com.qxy.victory.persistence.VideoDao
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class VideoRepository @Inject constructor(
  private val dyClient: DyClient,
  private val videoDao: VideoDao,
  private val ioDispatcher: CoroutineDispatcher
) : Repository {

  @WorkerThread
  fun fetchVideoList(
    onStart: () -> Unit,
    onComplete: () -> Unit,
    onError: (String?) -> Unit
  ) = flow {

    var rankList = videoDao.getAllList()

    Log.d("AlphTest", "fetchVideoList: ")

    for (videoData in rankList) {
      Log.d("AlphTest", videoData.toString())
    }
    Log.d("AlphTest", "fetchVideoList: ")


    if (rankList.isEmpty()) {
      val req = dyClient.discoveryMockVideoList()

      req.suspendOnSuccess {
        rankList = data.data.list
//        Timber.d(rankList.toString())
//        Timber.d(data.data.list.toString())
        videoDao.insertList(rankList)
        emit(videoDao.getAllList())
      }.onFailure {
        onError(message())
        Log.d("AlphTest", "error" + message())
      }.onFailure {
        onError(message())
        Log.d("AlphTest", "onFailure" + message())
      }
    } else {
      emit(videoDao.getAllList())
    }
  }.onStart {
    onStart()
  }.onCompletion {
    onComplete()
  }.flowOn(ioDispatcher)


}




