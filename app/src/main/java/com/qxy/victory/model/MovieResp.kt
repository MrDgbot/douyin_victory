package com.qxy.victory.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class MovieResp(
  @field:Json(name = "data") val data: DataResponse,
  @field:Json(name = "extra") val message: Extra
) {
  @JsonClass(generateAdapter = true)
  data class DataResponse(
    @field:Json(name = "active_time") val activeTime: String?,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "error_code") val errorCode: Int?,
    @field:Json(name = "list") val list: List<MovieItem>
  )
}
