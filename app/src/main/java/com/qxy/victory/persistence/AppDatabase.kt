/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qxy.victory.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.qxy.victory.model.Follower
import com.qxy.victory.model.RankItem
import com.qxy.victory.model.VideoData

@Database(
  entities = [RankItem::class, VideoData::class, Follower::class],
  version = 6,
  exportSchema = true
)
@TypeConverters(value = [StringListTypeConverter::class])
abstract class AppDatabase : RoomDatabase() {

  abstract fun rankDao(): RankDao

  abstract fun followerDao(): FollowerDao

  abstract fun videoDao(): VideoDao
}
