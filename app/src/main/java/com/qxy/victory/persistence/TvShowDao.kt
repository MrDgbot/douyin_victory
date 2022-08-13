package com.qxy.victory.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.qxy.victory.model.ActorItem

@Dao
interface TvShowDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertShowList(tvShowItemList: List<ActorItem>)

  @Query("SELECT * FROM ActorItem WHERE page = :page_")
  suspend fun getShowList(page_: Int): List<ActorItem>

  @Query("SELECT * FROM ActorItem WHERE page <= :page_")
  suspend fun getAllShowList(page_: Int): List<ActorItem>

}