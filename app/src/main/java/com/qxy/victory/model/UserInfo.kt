package com.qxy.victory.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserInfo(
  @field:Json(name = "data") val user: DataResponse,
  @field:Json(name = "extra") val extra: Extra?
) {

  @JsonClass(generateAdapter = true)
  data class DataResponse(
    @field:Json(name = "union_id")  val unionId: String?,
    @field:Json(name = "avatar") val avatar: String?,
    @field:Json(name = "city") val city: String?,
    @field:Json(name = "country") val country: String?,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "e_account_role") val eAccountRole: String?,
    @field:Json(name = "error_code") val errorCode: String?,
    @field:Json(name = "gender") val gender: String?,
    @field:Json(name = "nickname") val nickname: String?,
    @field:Json(name = "open_id") val openId: String?,
    @field:Json(name = "province") val province: String?
  )
}
