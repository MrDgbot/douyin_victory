package com.qxy.victory.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class TitleFragmentPagesAdapter(var fmList: List<Fragment>, fm: FragmentManager?) : FragmentStatePagerAdapter(
  fm!!
) {
  override fun getCount()= fmList.size

  override fun getItem(position: Int)= fmList[position]
}