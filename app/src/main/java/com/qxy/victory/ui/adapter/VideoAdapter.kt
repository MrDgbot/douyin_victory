package com.qxy.victory.ui.adapter

import android.os.SystemClock
import android.view.ViewGroup
import android.webkit.WebView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.qxy.victory.R
import com.qxy.victory.databinding.ItemRankBinding
import com.qxy.victory.databinding.ItemVideoBinding
import com.qxy.victory.model.RankItem
import com.qxy.victory.model.VideoData
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding
import timber.log.Timber

class VideoAdapter: BindingListAdapter<VideoData, VideoAdapter.VideoViewHolder>(diffUtil) {

  inner class VideoViewHolder constructor(
    private val binding: ItemVideoBinding
  ) : RecyclerView.ViewHolder(binding.root) {

    init {
      binding.root.setOnClickListener {
        val position = bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION }
          ?: return@setOnClickListener

        Timber.d(binding.item?.shareUrl)

      }
    }

    fun bindPokemon(videoData: VideoData) {
      binding.item = videoData
      binding.executePendingBindings()
    }
  }
  companion object {
    private val diffUtil = object : DiffUtil.ItemCallback<VideoData>() {

      override fun areItemsTheSame(oldItem: VideoData, newItem: VideoData): Boolean =
        oldItem.videoId == newItem.videoId

      override fun areContentsTheSame(oldItem: VideoData, newItem: VideoData): Boolean =
        oldItem == newItem
    }
  }

  override fun onBindViewHolder(holder: VideoViewHolder, position: Int)=
    holder.bindPokemon(getItem(position))

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder =
    parent.binding<ItemVideoBinding>(R.layout.item_video).let(::VideoViewHolder)
}