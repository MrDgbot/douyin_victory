/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qxy.victory.di

import com.qxy.victory.network.HttpRequestInterceptor
import com.qxy.victory.network.DyClient
import com.qxy.victory.network.DyService
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  @Singleton
  fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(HttpRequestInterceptor())
      .build()
  }

  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .client(okHttpClient)
      .baseUrl("https://open.douyin.com")
      .addConverterFactory(MoshiConverterFactory.create())
      .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
      .build()
  }//.baseUrl("https://www.fastmock.site/mock/26ad7e30cf27a9e2cc7477c13b148794/api/")

  @Provides
  @Singleton
  fun provideDyService(retrofit: Retrofit): DyService {
    return retrofit.create(DyService::class.java)
  }

  @Provides
  @Singleton
  fun provideDyClient(dyService: DyService): DyClient {
    return DyClient(dyService)
  }
}
