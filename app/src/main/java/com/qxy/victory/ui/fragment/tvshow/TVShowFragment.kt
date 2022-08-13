package com.qxy.victory.ui.fragment.tvshow

import android.os.Bundle
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import com.qxy.victory.R
import com.qxy.victory.databinding.TvshowFragmentBinding
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TVShowFragment : BindingFragment<TvshowFragmentBinding>(R.layout.tvshow_fragment) {

  @get:VisibleForTesting
  internal val viewModel: TVShowViewModel by viewModels()


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding {
      vm = viewModel
    }
  }
}