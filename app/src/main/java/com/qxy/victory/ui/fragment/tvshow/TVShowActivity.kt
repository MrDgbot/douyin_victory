package com.qxy.victory.ui.fragment.tvshow

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.qxy.victory.R
import com.qxy.victory.databinding.ActivityShowBinding
import com.qxy.victory.model.ActorItem
import com.qxy.victory.ui.adapter.TvShowAdapter
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.intentOf
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationLayout
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TVShowActivity : BindingActivity<ActivityShowBinding>(R.layout.activity_show) {
  @get:VisibleForTesting
  internal val viewModel: TVShowViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    onTransformationStartContainer()
    super.onCreate(savedInstanceState)
    binding {
      adapter = TvShowAdapter()
      tvvm = viewModel
    }
  }

  companion object {
    @VisibleForTesting
    internal const val EXTRA_POKEMON = "EXTRA_POKEMON"

    fun startActivity(transformationLayout: TransformationLayout, actorItem: ActorItem) =
      transformationLayout.context.intentOf<TVShowActivity> {
        //putExtra(EXTRA_POKEMON to actorItem)
        TransformationCompat.startActivity(transformationLayout, intent)
      }
  }
}