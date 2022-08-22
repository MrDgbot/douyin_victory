package com.qxy.victory.ui.fragment.follow

import com.qxy.victory.repository.FollowerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FansViewModel @Inject constructor(
  private val followerRepository: FollowerRepository
) : BaseFollowViewModel(2,followerRepository){

}