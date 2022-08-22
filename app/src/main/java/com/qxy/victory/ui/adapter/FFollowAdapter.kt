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
import com.qxy.victory.databinding.ItemRankBinding
import com.qxy.victory.model.RankItem

class FFollowAdapter : BindingListAdapter<RankItem, FFollowAdapter.RankViewHolder>(diffUtil) {

  private var onClickedAt = 0L

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankViewHolder =
    parent.binding<ItemRankBinding>(R.layout.item_rank).let(::RankViewHolder)

  override fun onBindViewHolder(holder: RankViewHolder, position: Int) =
    holder.bindPokemon(getItem(position))

  inner class RankViewHolder constructor(
    private val binding: ItemRankBinding
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

    fun bindPokemon(rankItem: RankItem) {
      binding.item = rankItem
      binding.executePendingBindings()
    }
  }

  companion object {
    private val diffUtil = object : DiffUtil.ItemCallback<RankItem>() {

      override fun areItemsTheSame(oldItem: RankItem, newItem: RankItem): Boolean =
        oldItem.name == newItem.name

      override fun areContentsTheSame(oldItem: RankItem, newItem: RankItem): Boolean =
        oldItem == newItem
    }
  }
}
