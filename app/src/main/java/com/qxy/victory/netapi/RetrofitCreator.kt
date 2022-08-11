package com.qxy.victory.netapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitCreator {
    companion object{
        private const val BASE_URL="https://open.douyin.com/oauth"
        private val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()


        fun <T> create(serviceClass: Class<T>):T = retrofit.create(serviceClass)

    }




}