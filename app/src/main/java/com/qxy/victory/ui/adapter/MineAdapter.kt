package com.qxy.victory.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.qxy.victory.ui.fragment.video.VideoFragment

class MineAdapter(fragmentActivity: FragmentActivity, private var totalCount: Int) :
  FragmentStateAdapter(fragmentActivity) {

  override fun getItemCount(): Int {
    return totalCount
  }

  override fun createFragment(position: Int): Fragment {

    return when (position) {
      0 -> VideoFragment()
      else -> VideoFragment()
    }
  }

}
