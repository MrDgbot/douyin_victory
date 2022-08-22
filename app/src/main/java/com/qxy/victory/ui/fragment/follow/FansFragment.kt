package com.qxy.victory.ui.fragment.follow

import android.os.Bundle
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import com.qxy.victory.R
import com.qxy.victory.databinding.FragmentFansBinding
import com.qxy.victory.ui.adapter.FFollowAdapter
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FansFragment : BindingFragment<FragmentFansBinding>(R.layout.fragment_fans) {
  @get:VisibleForTesting
  internal val viewModel: FansViewModel by viewModels()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding {
      vm = viewModel
      adapter = FFollowAdapter()
    }
  }
}