package com.qxy.victory.ui.fragment.series

import android.os.Bundle
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import com.qxy.victory.R
import com.qxy.victory.databinding.ToolbarSeriesBinding
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesFragment : BindingFragment<ToolbarSeriesBinding>(R.layout.toolbar_series) {

  @get:VisibleForTesting
  internal val viewModel: SeriesViewModel by viewModels()


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding {
      vm = viewModel
    }
  }
}