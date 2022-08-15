package com.qxy.victory.ui.activity.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.FragmentActivity
import com.qxy.victory.R
import com.qxy.victory.databinding.ActivityMainBinding
import com.qxy.victory.ui.adapter.ViewPagerAdapter
import com.skydoves.bindables.BindingActivity
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

  @get:VisibleForTesting
  internal val viewModel: MainViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    onTransformationStartContainer()
    super.onCreate(savedInstanceState)

    val myFragmentActivity: FragmentActivity = this
    binding {
      vm = viewModel
      adapter = ViewPagerAdapter(myFragmentActivity, 2)

    }
  }
}
