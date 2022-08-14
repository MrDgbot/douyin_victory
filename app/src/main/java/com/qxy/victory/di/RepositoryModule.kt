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

import com.qxy.victory.network.DyClient
import com.qxy.victory.persistence.PokemonDao
import com.qxy.victory.persistence.PokemonInfoDao
import com.qxy.victory.persistence.SeriesDao
import com.qxy.victory.repository.DetailRepository
import com.qxy.victory.repository.MovieRepository
import com.qxy.victory.repository.SeriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

  @Provides
  @ViewModelScoped
  fun provideMainRepository(
    dyClient: DyClient,
    pokemonDao: PokemonDao,
    coroutineDispatcher: CoroutineDispatcher
  ): MovieRepository {
    return MovieRepository(dyClient, pokemonDao, coroutineDispatcher)
  }

  @Provides
  @ViewModelScoped
  fun provideSeriesRepository(
    dyClient: DyClient,
    pokemonDao: SeriesDao,
    coroutineDispatcher: CoroutineDispatcher
  ): SeriesRepository {
    return SeriesRepository(dyClient, pokemonDao, coroutineDispatcher)
  }

  @Provides
  @ViewModelScoped
  fun provideDetailRepository(
    pokedexClient: DyClient,
    pokemonInfoDao: PokemonInfoDao,
    coroutineDispatcher: CoroutineDispatcher
  ): DetailRepository {
    return DetailRepository(pokedexClient, pokemonInfoDao, coroutineDispatcher)
  }
}
