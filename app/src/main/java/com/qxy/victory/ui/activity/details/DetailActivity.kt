package com.qxy.victory.ui.activity.details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.bundleNonNull
import com.skydoves.bundler.intentOf
import com.qxy.victory.R
import com.qxy.victory.databinding.ActivityDetailBinding
import com.qxy.victory.model.MovieItem
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationCompat.onTransformationEndContainerApplyParams
import com.skydoves.transformationlayout.TransformationLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : BindingActivity<ActivityDetailBinding>(R.layout.activity_detail) {

  @set:Inject
  internal lateinit var detailViewModelFactory: DetailViewModel.AssistedFactory

  @get:VisibleForTesting
  internal val viewModel: DetailViewModel by viewModels {
    DetailViewModel.provideFactory(detailViewModelFactory, movieItemItem.name)
  }

  private val movieItemItem: MovieItem by bundleNonNull(EXTRA_POKEMON)

  override fun onCreate(savedInstanceState: Bundle?) {
    onTransformationEndContainerApplyParams(this)
    super.onCreate(savedInstanceState)
    binding {
      pokemon = movieItemItem
      vm = viewModel
    }
  }

  companion object {
    @VisibleForTesting
    internal const val EXTRA_POKEMON = "EXTRA_POKEMON"

    fun startActivity(transformationLayout: TransformationLayout, movieItem: MovieItem) =
      transformationLayout.context.intentOf<DetailActivity> {
        putExtra(EXTRA_POKEMON to movieItem)
        TransformationCompat.startActivity(transformationLayout, intent)
      }
  }
}
