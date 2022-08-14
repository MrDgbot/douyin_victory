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

package com.qxy.victory.utils

import com.qxy.victory.model.MovieItem

object MockUtil {

  fun mockMovieItem() = MovieItem(
    id = "1",
    page = 0,
    name = "bulbasaur",
    poster = "https://p9-dy.byteimg.com/obj/compass/93204d13cf5a4fb080e74ea6d057eca1?from=552310259",
    discussionHot = 3139443,
    hot = 12787444,
    influenceHot = 943262,
    maoyanId = "1359034",
    name_en = "Moon Man",
    releaseDate = "2022-07-29",
    searchHot = 1112980,
    topicHot = 1112980,
    type = 1
  )

  fun mockPokemonList() = listOf(mockMovieItem())

//  fun mockPokemonInfo() = UserInfo(
//    id = 1,
//    name = "bulbasaur",
//    height = 7,
//    weight = 69,
//    experience = 60,
//    types = emptyList()
//  )
}