package com.qxy.victory.repository

import androidx.annotation.WorkerThread
import com.qxy.victory.network.DyClient
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

class UserInfoRepository @Inject constructor(
  private val dyClient: DyClient,
  private val ioDispatcher: CoroutineDispatcher
) : Repository {

  @WorkerThread
  fun getUserInfo(
    onStart: () -> Unit,
    onComplete: () -> Unit,
    onError: (String?) -> Unit
  ) = flow {
    Timber.d("进入UserInfoRepository")
    val response = dyClient.oauthUserInfo()
    response.suspendOnSuccess {
      Timber.d("response Data: $data")
      if (data.user.avatar?.isNotEmpty() == true) {
        Timber.d("user avatar: ${data.user.avatar}")
        Timber.d("user: ${data.user}")
        emit(data.user)
      }
    }.onFailure {
      Timber.d("失败: ${message()}")
      onError(message())
    }
  }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)

}
