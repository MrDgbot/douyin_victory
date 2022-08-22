package com.qxy.victory.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.qxy.victory.model.Follower

@Dao
interface FollowerDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertList(followerList: List<Follower>)

  @Query("SELECT * FROM Follower WHERE page = :page_ AND type = :type_")
  suspend fun getList(page_: Int, type_: Int): List<Follower>

  @Query("SELECT * FROM Follower WHERE page <= :page_ AND type = :type_")
  suspend fun getAllList(page_: Int, type_: Int): List<Follower>
}