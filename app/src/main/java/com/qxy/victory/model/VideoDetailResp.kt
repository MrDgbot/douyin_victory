package com.qxy.victory.model
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class VideoDetailResp(
    @Json(name = "data")
    val `data`: Data,
    @Json(name = "extra")
    val extra: Extra
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "description")
        val description: String,
        @Json(name = "error_code")
        val errorCode: Int, // 0
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
        val logid: String, // 2022082100583601021120601349914CE1
        @Json(name = "now")
        val now: Int, // 1661014717
        @Json(name = "sub_description")
        val subDescription: String,
        @Json(name = "sub_error_code")
        val subErrorCode: Int // 0
    )
}