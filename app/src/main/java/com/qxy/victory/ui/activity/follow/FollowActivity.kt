package com.qxy.victory.ui.activity.follow

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.FragmentActivity
import com.qxy.victory.R
import com.qxy.victory.databinding.ActivityFollowBinding
import com.qxy.victory.ui.adapter.FollowAdapter
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.intentOf
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationLayout
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowActivity : BindingActivity<ActivityFollowBinding>(R.layout.activity_follow) {

  @get:VisibleForTesting
  internal val viewModel: FollowViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    onTransformationStartContainer()
    super.onCreate(savedInstanceState)

    val myFragmentActivity: FragmentActivity = this
    binding {
      vm = viewModel
      adapter = FollowAdapter(myFragmentActivity, 5)
    }

  }


  companion object {
    @VisibleForTesting
    internal const val EXTRA_POKEMON = "Movie"

    fun startActivity(transformationLayout: TransformationLayout) =
      transformationLayout.context.intentOf<FollowActivity> {
        putExtra(EXTRA_POKEMON to 1)
        TransformationCompat.startActivity(transformationLayout, intent)
      }
  }
}