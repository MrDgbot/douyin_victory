package com.qxy.victory.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RankResp(
  @Json(name = "data")
  val data: Data,
  @Json(name = "extra")
  val extra: Extra
) {
  @JsonClass(generateAdapter = true)
  data class Data(
    @Json(name = "active_time") val activeTime: String,
    @Json(name = "description") val description: String,
    @Json(name = "error_code") val errorCode: Int,
    @Json(name = "list") val list: List<RankItem>
  )
  @JsonClass(generateAdapter = true)
  data class Extra(
    @Json(name = "logid") val logid: String,
    @Json(name = "now") val now: Long
  )
}