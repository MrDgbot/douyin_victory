package com.qxy.victory.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FollowerResp(
  @Json(name = "data") val data: RespData,
  @Json(name = "extra") val extra: Extra
) {
  @JsonClass(generateAdapter = true)
  data class RespData(
    @field:Json(name = "cursor") val cursor: Int?,
    @field:Json(name = "list") val list: List<Follower>?,
    @field:Json(name = "has_more") val hasMore: Boolean?
  )
}