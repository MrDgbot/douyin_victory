package com.qxy.victory.ui.activity.follow

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.qxy.victory.R
import com.qxy.victory.databinding.ActivityFollowBinding
import com.qxy.victory.ui.adapter.TitleFragmentPagesAdapter
import com.qxy.victory.ui.fragment.follow.FansFragment
import com.qxy.victory.ui.fragment.follow.FollowerFragment
import com.skydoves.bindables.BindingActivity
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowActivity : BindingActivity<ActivityFollowBinding>(R.layout.activity_follow), TabLayout.OnTabSelectedListener {

  @get:VisibleForTesting
  internal val viewModel: FollowViewModel by viewModels()

  //val sectionsPagerAdapter = TitleFragmentPagesAdapter({}, supportFragmentManager)
  val vp_content: ViewPager = findViewById(R.id.vp_content)
  val tl_table: TabLayout = findViewById(R.id.tl_table)

  override fun onCreate(savedInstanceState: Bundle?) {
    onTransformationStartContainer()
    super.onCreate(savedInstanceState)
    val myFragmentActivity: FragmentActivity = this
    binding {
      vm = viewModel
    }
    initView()
    setTab()
    setItem()
    vp_content.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tl_table))
  }
  //初始化界面
  private fun initView() {
    setContentView(R.layout.activity_follow)
  }
  //设置Tab
  private fun setTab() {
    val tab1 = FollowerFragment()
    val tab2 = FansFragment()
    var list = listOf<Fragment>(tab1, tab2)

    vp_content.adapter = TitleFragmentPagesAdapter(list, supportFragmentManager)
    tl_table.setupWithViewPager(vp_content)

  }

  //设置条目
  fun setItem() {
    val list = listOf<String>("关注", "粉丝")
    tl_table.getTabAt(0)?.text = list[0]
    tl_table.getTabAt(1)?.text = list[1]

  }

  override fun onTabSelected(p0: TabLayout.Tab?) {
    TODO("Not yet implemented")
    vp_content.currentItem = p0!!.position
  }

  override fun onTabUnselected(p0: TabLayout.Tab?) {
    TODO("Not yet implemented")
    vp_content.currentItem = p0!!.position

  }

  override fun onTabReselected(p0: TabLayout.Tab?) {
    TODO("Not yet implemented")

  }
}