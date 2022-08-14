package com.qxy.victory.ui.adapter

import android.os.SystemClock
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.qxy.victory.R
import com.qxy.victory.databinding.ItemTvshowBinding
import com.qxy.victory.model.ShowItem
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding

class ShowAdapter : BindingListAdapter<ShowItem, ShowAdapter.TvShowViewHolder>(diffUtil) {

  private var onClickedAt = 0L

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):TvShowViewHolder =
    parent.binding<ItemTvshowBinding>(R.layout.item_tvshow).let(::TvShowViewHolder)

  override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) =
    holder.bindPokemon(getItem(position))

  inner class TvShowViewHolder constructor(
    private val binding: ItemTvshowBinding
  ) : RecyclerView.ViewHolder(binding.root) {

    init {
      binding.root.setOnClickListener {
        val position = bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION }
          ?: return@setOnClickListener
        val currentClickedAt = SystemClock.elapsedRealtime()
        if (currentClickedAt - onClickedAt > binding.transformationLayout.duration) {
          // 单个Item跳转
//          TVShowActivity.startActivity(binding.transformationLayout, getItem(position))
//          onClickedAt = currentClickedAt
        }
      }
    }

    fun bindPokemon(actorItem: ShowItem) {
      binding.item=actorItem
      binding.executePendingBindings()
    }
  }

  companion object {
    private val diffUtil = object : DiffUtil.ItemCallback<ShowItem>() {

      override fun areItemsTheSame(oldItem: ShowItem, newItem: ShowItem): Boolean =
        oldItem.name == newItem.name

      override fun areContentsTheSame(oldItem: ShowItem, newItem: ShowItem): Boolean =
        oldItem == newItem
    }
  }


}
