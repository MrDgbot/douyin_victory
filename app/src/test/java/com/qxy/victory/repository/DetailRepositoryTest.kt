///*
// * Designed and developed by 2020 skydoves (Jaewoong Eum)
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// * http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//@file:Suppress("SpellCheckingInspection")
//
//package com.qxy.victory.repository
//
//import app.cash.turbine.test
//import com.nhaarman.mockitokotlin2.atLeastOnce
//import com.nhaarman.mockitokotlin2.mock
//import com.nhaarman.mockitokotlin2.verify
//import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
//import com.nhaarman.mockitokotlin2.whenever
//import com.qxy.victory.MainCoroutinesRule
//import com.qxy.victory.network.DyClient
//import com.qxy.victory.network.DyService
//import com.qxy.victory.persistence.PokemonInfoDao
//import com.qxy.victory.utils.MockUtil.mockPokemonInfo
//import com.skydoves.sandwich.ApiResponse
//import kotlinx.coroutines.test.runTest
//import org.junit.Assert.assertEquals
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import retrofit2.Response
//import kotlin.time.DurationUnit
//import kotlin.time.toDuration
//
//class DetailRepositoryTest {
//
//  private lateinit var repository: DetailRepository
//  private lateinit var client: DyClient
//  private val service: DyService = mock()
//  private val pokemonInfoDao: PokemonInfoDao = mock()
//
//  @get:Rule
//  val coroutinesRule = MainCoroutinesRule()
//
//  @Before
//  fun setup() {
//    client = DyClient(service)
//    repository = DetailRepository(client, pokemonInfoDao, coroutinesRule.testDispatcher)
//  }
//
//  @Test
//  fun fetchPokemonInfoFromNetworkTest() = runTest {
//    val mockData = mockPokemonInfo()
//    whenever(pokemonInfoDao.getPokemonInfo(name_ = "bulbasaur")).thenReturn(null)
//    whenever(service.fetchPokemonInfo(name = "bulbasaur")).thenReturn(
//      ApiResponse.of {
//        Response.success(
//          mockData
//        )
//      }
//    )
//
//    repository.fetchPokemonInfo(name = "bulbasaur", onComplete = {}, onError = {}).test {
//      val expectItem = requireNotNull(awaitItem())
//      assertEquals(expectItem.id, mockData.id)
//      assertEquals(expectItem.name, mockData.name)
//      assertEquals(expectItem, mockData)
//      awaitComplete()
//    }
//
//    verify(pokemonInfoDao, atLeastOnce()).getPokemonInfo(name_ = "bulbasaur")
//    verify(service, atLeastOnce()).fetchPokemonInfo(name = "bulbasaur")
//    verify(pokemonInfoDao, atLeastOnce()).insertPokemonInfo(mockData)
//    verifyNoMoreInteractions(service)
//  }
//
//  @Test
//  fun fetchPokemonInfoFromDatabaseTest() = runTest {
//    val mockData = mockPokemonInfo()
//    whenever(pokemonInfoDao.getPokemonInfo(name_ = "bulbasaur")).thenReturn(mockData)
//    whenever(service.fetchPokemonInfo(name = "bulbasaur")).thenReturn(
//      ApiResponse.of {
//        Response.success(
//          mockData
//        )
//      }
//    )
//
//    repository.fetchPokemonInfo(
//      name = "bulbasaur", onComplete = {}, onError = {}
//    ).test(5.toDuration(DurationUnit.SECONDS)) {
//      val expectItem = requireNotNull(awaitItem())
//      assertEquals(expectItem.id, mockData.id)
//      assertEquals(expectItem.name, mockData.name)
//      assertEquals(expectItem, mockData)
//      awaitComplete()
//    }
//
//    verify(pokemonInfoDao, atLeastOnce()).getPokemonInfo(name_ = "bulbasaur")
//    verifyNoMoreInteractions(service)
//  }
//}
