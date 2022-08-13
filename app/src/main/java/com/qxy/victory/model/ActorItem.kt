package com.qxy.victory.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class ActorItem(
  var page: Int = 0,
//  @Json(name = "actors")
//  val actors: List<String>,
//  @Json(name = "areas")
//  val areas: List<String>,
//  @Json(name = "directors")
//  val directors: List<String>,
  @Json(name = "discussion_hot")
  val discussionHot: Int, // 3117282
  @Json(name = "hot")
  val hot: Int, // 12655786
  @PrimaryKey
  @Json(name = "id")
  val id: String, // 6903365126108381703
  @Json(name = "influence_hot")
  val influenceHot: Int, // 944334
  @Json(name = "maoyan_id")
  val maoyanId: String, // 1359034
  @Json(name = "name")
  val name: String, // 独行月球
  @Json(name = "name_en")
  val nameEn: String, // Moon Man
  @Json(name = "poster")
  val poster: String, // https://p9-dy.byteimg.com/obj/compass/93204d13cf5a4fb080e74ea6d057eca1?from=552310259
  @Json(name = "release_date")
  val releaseDate: String, // 2022-07-29
  @Json(name = "search_hot")
  val searchHot: Int, // 1217075
//  @Json(name = "tags")
//  val tags: List<String>,
  @Json(name = "topic_hot")
  val topicHot: Int, // 1099499
  @Json(name = "type")
  val type: Int // 1
)