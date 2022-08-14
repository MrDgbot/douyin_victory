package com.qxy.victory.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.qxy.victory.model.ShowItem

@Dao
interface ShowDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertShowList(tvShowItemList: List<ShowItem>)

  @Query("SELECT * FROM ShowItem WHERE page = :page_")
  suspend fun getShowList(page_: Int): List<ShowItem>

  @Query("SELECT * FROM ShowItem WHERE page <= :page_")
  suspend fun getAllShowList(page_: Int): List<ShowItem>

}