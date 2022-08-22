package com.qxy.victory.ui.fragment.follow

import com.qxy.victory.repository.FollowerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FollowerViewModel @Inject constructor(
  private val followerRepository: FollowerRepository
) : BaseFollowViewModel(1,followerRepository) {

}