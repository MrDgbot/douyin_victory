package com.qxy.victory.model

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class ShowResp(
  @Json(name = "data")
    val data: Data,
  @Json(name = "extra")
    val extra: Extra
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "active_time")
        val activeTime: String, // 2022-08-12
        @Json(name = "description")
        val description: String,
        @Json(name = "error_code")
        val errorCode: Int, // 0
        @Json(name = "list")
        val list: List<ShowItem>
    )
    @JsonClass(generateAdapter = true)
    data class Extra(
        @Json(name = "logid")
        val logid: String, // 202208122353200102090440402621FC72
        @Json(name = "now")
        val now: Long // 1660319600680
    )
}