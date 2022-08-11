package com.qxy.victory.netapi

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object BoardNetwork {
    private val boardService=RetrofitCreator.create(BoardService::class.java)

    suspend fun getBoards(id:Int)= boardService.fetchBoards(id).await()

    private suspend fun <T> Call<T>.await():T{
        return suspendCoroutine {
            continuation ->enqueue(object :Callback<T>{
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body=response.body()
                    if(body!=null)
                        continuation.resume(body)
                    else
                        continuation.resumeWithException(
                            RuntimeException("Response body is null")
                        )
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    Log.d("BoardNetWork", "onFailure: ")
                }
            })
        }
    }
}