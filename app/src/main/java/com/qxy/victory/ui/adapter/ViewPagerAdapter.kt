package com.qxy.victory.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.qxy.victory.ui.fragment.home.HomeFragment
import com.qxy.victory.ui.fragment.mine.MineFragment
import com.qxy.victory.ui.fragment.movie.MovieFragment
import timber.log.Timber

class ViewPagerAdapter(fragmentActivity: FragmentActivity, private var totalCount: Int) :
  FragmentStateAdapter(fragmentActivity) {

  override fun getItemCount(): Int {
    return totalCount
  }

  override fun createFragment(position: Int): Fragment {
    Timber.d("Position :$position")
    return when (position) {
      0 -> HomeFragment()
      4 -> MineFragment()
      else -> MovieFragment()
    }
  }

}
