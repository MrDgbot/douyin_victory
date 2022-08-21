package com.qxy.victory.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
@JsonClass(generateAdapter = true)
data class VideoData(
  @Json(name = "cover")
  val cover: String, // https://p26-sign.douyinpic.com/tos-cn-p-0015/05bda9e3e05b4cb0b951dc68cd82913f~c5_300x400.jpeg?x-expires=1662220800&x-signature=IkXntYtcHGSfnTt%2BMdifl9BVSuY%3D&from=4257465056_large&s=PackSourceEnum_PUBLISH&se=false&sc=cover&l=202208210047400102080372020690215A
  @Json(name = "create_time")
  val createTime: Int, // 1661013126
  @Json(name = "is_reviewed")
  val isReviewed: Boolean, // true
  @Json(name = "is_top")
  val isTop: Boolean, // false
  @Json(name = "item_id")
  val itemId: String, // @9VwD2vKYRMthb3/5cYEpF8791GbqNPGEM5dwrw+iJ1IXavf460zdRmYqig357zEBczXhR7C9E5ZLMpUQgS2EYQ==
  @Json(name = "media_type")
  val mediaType: Int, // 4
  @Json(name = "share_url")
  val shareUrl: String, // https://www.iesdouyin.com/share/video/7133996821654834464/?region=CN&mid=7133997043072207630&u_code=1165dmgb1&did=MS4wLjABAAAANwkJuWIRFOzg5uCpDRpMj4OX-QryoDgn-yYlXQnRwQQ&iid=MS4wLjABAAAANwkJuWIRFOzg5uCpDRpMj4OX-QryoDgn-yYlXQnRwQQ&with_sec_did=1&titleType=title
  @Embedded
  @Json(name = "statistics")
  val statistics: Statistics,
  @Json(name = "title")
  val title: String, // 测试2
  @PrimaryKey
  @Json(name = "video_id")
  val videoId: String, // 7133996821654834464
  @Json(name = "video_status")
  val videoStatus: Int // 1
) : Parcelable {
  @Parcelize
  @JsonClass(generateAdapter = true)
  data class Statistics(
    @Json(name = "comment_count")
    val commentCount: Int, // 0
    @Json(name = "digg_count")
    val diggCount: Int, // 0
    @Json(name = "download_count")
    val downloadCount: Int, // 0
    @Json(name = "forward_count")
    val forwardCount: Int, // 0
    @Json(name = "play_count")
    val playCount: Int, // 43
    @Json(name = "share_count")
    val shareCount: Int // 0
  ) : Parcelable
}