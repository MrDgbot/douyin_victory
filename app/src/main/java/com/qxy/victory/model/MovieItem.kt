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

package com.qxy.victory.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.qxy.victory.utils.NumberUtils
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import timber.log.Timber

@Entity
@JsonClass(generateAdapter = true)
data class MovieItem(
  var page: Int = 0,
  var index: Int = 0,
  @field:Json(name = "id") @PrimaryKey val id: String,
  @field:Json(name = "actors") val actors: List<String>? = null,
  @field:Json(name = "tags") val tags: List<String>? = null,
  @field:Json(name = "type") val type: Int,
  @field:Json(name = "name") val name: String,
  @field:Json(name = "poster") val poster: String,
  @field:Json(name = "name_en") val name_en: String,
  @field:Json(name = "maoyan_id") val maoyanId: String,
  @field:Json(name = "release_date") val releaseDate: String,
  @field:Json(name = "hot") val hot: Long,
  @field:Json(name = "discussion_hot") val discussionHot: Long,
  @field:Json(name = "influence_hot") val influenceHot: Long,
  @field:Json(name = "search_hot") val searchHot: Long,
  @field:Json(name = "topic_hot") val topicHot: Long
) {

  fun getActorsString(): String {
    if (actors.isNullOrEmpty()) return ""
    val len = if (actors.size < 3) actors.size else 3
    Timber.d("actors size: ${actors.size}")
    Timber.d("actors : ${actors.toString()}")
    return actors.take(len).joinToString(separator = "/") ?: ""
  }

  fun getTagsString(): String {
    if (tags.isNullOrEmpty()) return ""
    val len = if (tags.size < 2) tags.size else 2
    return "  [" + tags.take(len).joinToString(separator = "|") + "]"
  }
  fun getDiscussionString(): String = NumberUtils().formatNum(discussionHot.toString()).toString()
  fun getIndexString(): String = when (index) {
    1, 2, 3 -> "TOP${index}"
    else -> index.toString()
  }
}
