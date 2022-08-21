package com.qxy.victory.ui.fragment.video

import android.os.Bundle
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import com.qxy.victory.R
import com.qxy.victory.databinding.FragmentVideoBinding
import com.qxy.victory.ui.adapter.VideoAdapter
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class VideoFragment : BindingFragment<FragmentVideoBinding>(R.layout.fragment_video) {

  @get:VisibleForTesting
  internal val viewModel: VideoViewModel by viewModels()



  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding {
      vm = viewModel
      adapter = VideoAdapter()
    }
  }

}