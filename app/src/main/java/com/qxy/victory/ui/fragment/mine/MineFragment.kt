package com.qxy.victory.ui.fragment.mine

import android.os.Bundle
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import com.qxy.victory.R
import com.qxy.victory.databinding.FragmentMineBinding
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MineFragment : BindingFragment<FragmentMineBinding>(R.layout.fragment_mine) {

  @get:VisibleForTesting
  internal val viewModel: MineViewModel by viewModels()



  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding {
      vm = viewModel
    }
  }
}