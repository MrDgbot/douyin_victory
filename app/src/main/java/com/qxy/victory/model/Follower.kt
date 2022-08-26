package com.qxy.victory.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import timber.log.Timber

/**
 * Entity of follower or followed user
 * containing info that can be displayed on follower and following list.
 */
@Entity
@JsonClass(generateAdapter = true)
data class Follower(
  var page: Int = 0,
  var index: Int = 0,
  var type: Int = 0,
  //头像；国家、省份、城市；昵称；性别
  @field:Json(name = "nickname") val nickName: String?,
  @field:Json(name = "avatar") val avatar: String?,
  @field:Json(name = "country") val country: String,
  @field:Json(name = "province") val province: String?,
  @field:Json(name = "city") val city: String?,
  @field:Json(name = "gender") val gender: Int?, //性别: `0` - 未知， `1` - 男性， `2` - 女性
  @field:Json(name = "union_id") val unionId: String?,
  @field:Json(name = "open_id") @PrimaryKey val openId: String,
) {
  fun getGenderString(): String {
    return when (gender) {
      1 -> "♂ 男"
      2 -> "♀ 女"
      else -> "未知"
    }
  }

  fun getLocationString(): String {
    val location = if (province == city) {
      province!!
    } else {
      "$province${city}"
    }
    Timber.d(
      "$nickName: $country$location"
    )
    return "$country$location".trim()

  }
}
