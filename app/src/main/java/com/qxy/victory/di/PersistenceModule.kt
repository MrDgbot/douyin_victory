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

import android.app.Application
import androidx.room.Room
import com.qxy.victory.persistence.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

  @Provides
  @Singleton
  fun provideMoshi(): Moshi {
    return Moshi.Builder()
      .addLast(KotlinJsonAdapterFactory())
      .build()
  }

  @Provides
  @Singleton
  fun provideAppDatabase(
    application: Application,
    stringListTypeConverter: StringListTypeConverter
  ): AppDatabase {
    return Room
      .databaseBuilder(application, AppDatabase::class.java, "Victory.db")
      .fallbackToDestructiveMigration()
      .addTypeConverter(stringListTypeConverter)
      .build()
  }

  @Provides
  @Singleton
  fun provideRankDao(appDatabase: AppDatabase): RankDao {
    return appDatabase.rankDao()
  }

  @Provides
  @Singleton
  fun provideVideoDao(appDatabase: AppDatabase): VideoDao {
    return appDatabase.videoDao()
  }
  @Provides
  @Singleton
  fun provideFollowerDao(appDatabase: AppDatabase): FollowerDao {
    return appDatabase.followerDao()
  }


  @Provides
  @Singleton
  fun provideStringListTypeConverter(moshi: Moshi): StringListTypeConverter {
    return StringListTypeConverter(moshi)
  }



}
