package com.qxy.victory.repository

import androidx.annotation.WorkerThread
import com.qxy.victory.network.DyClient
import com.qxy.victory.persistence.RankDao
import com.qxy.victory.utils.Constants
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


class AuthRepository @Inject constructor(
  private val dyClient: DyClient,
  private val rankDao: RankDao,
  private val ioDispatcher: CoroutineDispatcher
) : Repository {

  @WorkerThread
  fun getToken(
    onStart: () -> Unit,
    onComplete: () -> Unit,
    onError: (String?) -> Unit
  ) = flow {


    val response = dyClient.oauthAcToken()

    response.suspendOnSuccess {
      if (data.data.accessToken.isEmpty()) {
        emit("授权失败，请重试")
      } else {
        Constants.ACT_TOKEN = data.data.accessToken
        Constants.OPEN_ID = data.data.openId!!
        Timber.d("授权成功，ACToken: ${Constants.ACT_TOKEN}")
        Timber.d("授权成功，OpenID: ${Constants.OPEN_ID}")
        emit("授权成功！")
      }
    }.onFailure {
      Timber.d(message())
      onError(message())
    }.onFailure { // handles the all error cases from the API request fails.
      onError(message())
    }

  }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)

}
