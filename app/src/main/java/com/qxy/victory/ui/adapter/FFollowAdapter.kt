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

package com.qxy.victory.ui.adapter

import android.os.SystemClock
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding
import com.qxy.victory.R
import com.qxy.victory.databinding.ItemFollowerBinding
import com.qxy.victory.model.Follower

class FFollowAdapter : BindingListAdapter<Follower, FFollowAdapter.FollowViewHolder>(diffUtil) {

  private var onClickedAt = 0L

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowViewHolder =
    parent.binding<ItemFollowerBinding>(R.layout.item_follower).let(::FollowViewHolder)

  override fun onBindViewHolder(holder: FollowViewHolder, position: Int) =
    holder.bindPokemon(getItem(position))

  inner class FollowViewHolder constructor(
    private val binding: ItemFollowerBinding
  ) : RecyclerView.ViewHolder(binding.root) {

    init {
      binding.root.setOnClickListener {
        val position = bindingAdapterPosition.takeIf { it != NO_POSITION }
          ?: return@setOnClickListener
        val currentClickedAt = SystemClock.elapsedRealtime()
        if (currentClickedAt - onClickedAt > binding.transformationLayout.duration) {
//          DetailActivity.startActivity(binding.transformationLayout, getItem(position))
//          onClickedAt = currentClickedAt
        }
      }
    }

    fun bindPokemon(follower: Follower) {
      binding.item = follower
      binding.executePendingBindings()
    }
  }

  companion object {
    private val diffUtil = object : DiffUtil.ItemCallback<Follower>() {

      override fun areItemsTheSame(oldItem: Follower, newItem: Follower): Boolean =
        oldItem.nickName == newItem.nickName

      override fun areContentsTheSame(oldItem: Follower, newItem: Follower): Boolean =
        oldItem == newItem
    }
  }
}
