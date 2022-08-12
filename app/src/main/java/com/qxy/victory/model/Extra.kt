package com.qxy.victory.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Extra(
  @field:Json(name = "logid") val logId: String,
  @field:Json(name = "now") val now: Long
)
