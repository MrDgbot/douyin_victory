package com.qxy.victory.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FollowerResp (
  @Json(name = "data")
  val data: Data,
  @Json(name = "extra")
  val extra: Extra
  ){
  @JsonClass(generateAdapter = true)
  data class Data(
    @Json(name = "cursor") val cursor: Int,
    @Json(name = "has_more") val hasMore: Boolean,
    @Json(name = "list") val list: List<Follower>
  )
  @JsonClass(generateAdapter = true)
  data class Extra(
    @Json(name = "logid") val logid: String,
    @Json(name = "now") val now: Long
  )
}