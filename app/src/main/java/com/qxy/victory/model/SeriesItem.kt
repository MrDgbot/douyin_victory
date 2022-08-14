package com.qxy.victory.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class SeriesItem(
  var page: Int = 0,
  @field:Json(name = "id") @PrimaryKey val id: String,
//  @field:Json(name = "actors") val actors: List<String>,
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
  @field:Json(name = "topic_hot") val topicHot: Int,
  //@field:Json(name = "directors") val director: List<String>
)