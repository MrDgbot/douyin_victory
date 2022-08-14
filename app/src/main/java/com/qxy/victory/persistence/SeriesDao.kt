package com.qxy.victory.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.qxy.victory.model.SeriesItem

@Dao
interface SeriesDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertSeriesList(SeriesItemList: List<SeriesItem>)

  @Query("SELECT * FROM SeriesItem WHERE page = :page_")
  suspend fun getSeriesList(page_: Int): List<SeriesItem>

  @Query("SELECT * FROM SeriesItem WHERE page <= :page_")
  suspend fun getAllSeriesList(page_: Int): List<SeriesItem>

}