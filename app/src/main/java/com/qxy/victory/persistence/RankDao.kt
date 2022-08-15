package com.qxy.victory.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.qxy.victory.model.RankItem

@Dao
interface RankDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertList(rankItemList: List<RankItem>)

  @Query("SELECT * FROM RankItem WHERE page = :page_ AND type = :type_")
  suspend fun getList(page_: Int, type_: Int): List<RankItem>

  @Query("SELECT * FROM RankItem WHERE page <= :page_ AND type = :type_")
  suspend fun getAllList(page_: Int, type_: Int): List<RankItem>

}