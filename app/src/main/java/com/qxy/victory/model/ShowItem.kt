package com.qxy.victory.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.qxy.victory.utils.NumberUtils
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import timber.log.Timber

@Entity
@JsonClass(generateAdapter = true)
data class ShowItem(
  var page: Int = 0,
  var index: Int = 0,
  @field:Json(name = "id") @PrimaryKey val id: String,
  @field:Json(name = "actors") val actors: List<String>? = null,
  @field:Json(name = "areas") val areas: List<String>? = null,
  @field:Json(name = "tag") val tags: List<String>? = null,
  @field:Json(name = "type") val type: Int,
  @field:Json(name = "name") val name: String,
  @field:Json(name = "poster") val poster: String,
  @field:Json(name = "name_en") val name_en: String,
  @field:Json(name = "maoyan_id") val maoyanId: String,
  @field:Json(name = "release_date") val releaseDate: String,
  @field:Json(name = "hot") val hot: Int,
  @field:Json(name = "discussion_hot") val discussionHot: Int,
  @field:Json(name = "influence_hot") val influenceHot: Long,
  @field:Json(name = "search_hot") val searchHot: Int,
  @field:Json(name = "topic_hot") val topicHot: Int
) {
  fun getActorsString(): String {
    if (actors.isNullOrEmpty()) return ""
    if (actors.isEmpty()) return ""
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

  fun getDiscussionHotString(): String =
    NumberUtils().formatNum(discussionHot.toString()).toString()

  fun getIndexString(): String = when (index) {
    1, 2, 3 -> "TOP${index}"
    else -> index.toString()
  }

}