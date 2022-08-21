package com.qxy.victory.model
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class VideoResp(
    @Json(name = "data")
    val data: Data,
    @Json(name = "extra")
    val extra: Extra
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "cursor")
        val cursor: Long, // 1643544944000
        @Json(name = "description")
        val description: String,
        @Json(name = "error_code")
        val errorCode: Int, // 0
        @Json(name = "has_more")
        val hasMore: Boolean, // false
        @Json(name = "list")
        val list: List<VideoData>
    ) {

    }

    @JsonClass(generateAdapter = true)
    data class Extra(
        @Json(name = "description")
        val description: String,
        @Json(name = "error_code")
        val errorCode: Int, // 0
        @Json(name = "logid")
        val logid: String, // 202208210047400102080372020690215A
        @Json(name = "now")
        val now: Int, // 1661014061
        @Json(name = "sub_description")
        val subDescription: String,
        @Json(name = "sub_error_code")
        val subErrorCode: Int // 0
    )
}