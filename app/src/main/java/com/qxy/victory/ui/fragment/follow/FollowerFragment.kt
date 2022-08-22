package com.qxy.victory.ui.fragment.follow

import android.os.Bundle
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import com.qxy.victory.R
import com.qxy.victory.databinding.FragmentFollowerBinding
import com.qxy.victory.ui.adapter.FFollowAdapter
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowerFragment : BindingFragment<FragmentFollowerBinding>(R.layout.fragment_follower) {
  @get:VisibleForTesting
  internal val viewModel: FollowerViewModel by viewModels()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding {
      vm = viewModel
      adapter= FFollowAdapter()
    }
  }
}