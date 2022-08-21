package com.qxy.victory.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.qxy.victory.model.RankItem
import com.qxy.victory.model.VideoData


@Dao
interface VideoDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertList(videoDataList: List<VideoData>)

  @Query("SELECT * FROM VideoData")
  suspend fun getAllList(): List<VideoData>

  @Query("SELECT shareUrl FROM VideoData")
  suspend fun getAllShareUrl(): List<String>
}