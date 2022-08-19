package com.qxy.victory.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import com.qxy.victory.R
import com.qxy.victory.databinding.FragmentHomeBinding
import com.qxy.victory.ui.activity.rank.movie.MovieActivity
import com.qxy.victory.ui.activity.rank.movie.series.SeriesActivity
import com.qxy.victory.ui.activity.rank.movie.show.ShowActivity
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

  @get:VisibleForTesting
  internal val viewModel: HomeViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val binding = FragmentHomeBinding.inflate(inflater, container, false)
    context ?: return binding.root

    binding.btnMovie.setOnClickListener {
      MovieActivity.startActivity(binding.transformationLayout)
    }

    binding.btnShows.setOnClickListener{
      ShowActivity.startActivity(binding.transformationLayout)
    }

    binding.btnTv.setOnClickListener{
      SeriesActivity.startActivity(binding.transformationLayout)
    }

    return binding.root
  }

//  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//    super.onViewCreated(view, savedInstanceState)
//    binding {
//      vm = viewModel
//    }
//  }
}