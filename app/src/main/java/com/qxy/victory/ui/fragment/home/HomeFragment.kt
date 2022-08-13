package com.qxy.victory.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import com.qxy.victory.R
import com.qxy.victory.databinding.HomeFragmentBinding
import com.qxy.victory.ui.activity.movie.MovieRankingActivity
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BindingFragment<HomeFragmentBinding>(R.layout.home_fragment) {

  @get:VisibleForTesting
  internal val viewModel: HomeViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val binding = HomeFragmentBinding.inflate(inflater, container, false)
    context ?: return binding.root

    binding.btnMovie.setOnClickListener(View.OnClickListener {
      MovieRankingActivity.startActivity(binding.transformationLayout)
    })

    return binding.root
  }

//  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//    super.onViewCreated(view, savedInstanceState)
//    binding {
//      vm = viewModel
//    }
//  }
}