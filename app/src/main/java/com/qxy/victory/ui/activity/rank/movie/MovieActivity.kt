package com.qxy.victory.ui.activity.rank.movie

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.intentOf
import com.qxy.victory.R
import com.qxy.victory.databinding.ActivityMovieBinding
import com.qxy.victory.ui.adapter.RankAdapter
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationCompat.onTransformationEndContainerApplyParams
import com.skydoves.transformationlayout.TransformationLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : BindingActivity<ActivityMovieBinding>(R.layout.activity_movie) {

  @get:VisibleForTesting
  internal val viewModel: MovieViewModel by viewModels()


  override fun onCreate(savedInstanceState: Bundle?) {
    onTransformationEndContainerApplyParams(this)
    super.onCreate(savedInstanceState)
    
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      // 设置状态栏透明
      window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }
    
    binding {
      vm = viewModel
      adapter = RankAdapter()
    }
  }

  companion object {
    @VisibleForTesting
    internal const val EXTRA_POKEMON = "Movie"

    fun startActivity(transformationLayout: TransformationLayout) =
      transformationLayout.context.intentOf<MovieActivity> {
        putExtra(EXTRA_POKEMON to 1)
        TransformationCompat.startActivity(transformationLayout, intent)
      }
  }
}
