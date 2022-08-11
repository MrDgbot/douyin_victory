package com.qxy.victory.netapi


import com.qxy.victory.model.TTMovie
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface BoardService {
    @Headers("access-token:act.238e6588eeaed5527880f63c857ac682uJcIj79LqD8LL8ZKhD1JSM1MWa8B")
    @GET("discovery/ent/rank/item")
     fun fetchBoards(@Query("type") id:Int): Call<ResponseBody>

//    @GET("/discovery/ent/rank/item/")
//    suspend fun fetchBoardList(): Call<Info>
//
//    @GET("/discovery/ent/rank/item/")
//    suspend fun fetchBoardList(): Call<Info>
}