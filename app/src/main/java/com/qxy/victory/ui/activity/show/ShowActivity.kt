package com.qxy.victory.ui.activity.show

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.qxy.victory.R
import com.qxy.victory.databinding.ActivityShowBinding
import com.qxy.victory.ui.adapter.RankAdapter
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.intentOf
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationLayout
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowActivity : BindingActivity<ActivityShowBinding>(R.layout.activity_show) {
  @get:VisibleForTesting
  internal val viewModel: ShowViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    onTransformationStartContainer()
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
    internal const val EXTRA_POKEMON = "EXTRA_POKEMON"

    fun startActivity(transformationLayout: TransformationLayout) =
      transformationLayout.context.intentOf<ShowActivity> {
        //putExtra(EXTRA_POKEMON to actorItem)
        TransformationCompat.startActivity(transformationLayout, intent)
      }
  }
}