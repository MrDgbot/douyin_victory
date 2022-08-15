/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qxy.victory.ui.activity.series

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.intentOf
import com.qxy.victory.R
import com.qxy.victory.databinding.ActivitySeriesBinding
import com.qxy.victory.ui.adapter.RankAdapter
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationCompat.onTransformationEndContainerApplyParams
import com.skydoves.transformationlayout.TransformationLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesRankingActivity : BindingActivity<ActivitySeriesBinding>(R.layout.activity_series) {

  @get:VisibleForTesting
  internal val viewModel: SeriesRankingViewModel by viewModels()


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
    internal const val EXTRA_POKEMON = "Series"

    fun startActivity(transformationLayout: TransformationLayout) =
      transformationLayout.context.intentOf<SeriesRankingActivity> {
        //putExtra(EXTRA_POKEMON to 2)
        TransformationCompat.startActivity(transformationLayout, intent)
      }
  }
}
