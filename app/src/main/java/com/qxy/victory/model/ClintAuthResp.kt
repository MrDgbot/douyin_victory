package com.qxy.victory.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ClintAuthResp(
    @field:Json(name = "data") val data: DataResponse,
    @field:Json(name = "message") val message: String?
) {
    @JsonClass(generateAdapter = true)
    data class DataResponse(
        @field:Json(name = "access_token") val accessToken: String,
        @field:Json(name = "open_id") val openId: String?,
        @field:Json(name = "captcha") val captcha: String?,
        @field:Json(name = "desc_url") val desc_url: String?,
        @field:Json(name = "description") val description: String?,
        @field:Json(name = "error_code") val errorCode: Int?,
        @field:Json(name = "expires_in") val expiresIn: Int?,
        @field:Json(name = "log_id") val logId: String?
    )
}
