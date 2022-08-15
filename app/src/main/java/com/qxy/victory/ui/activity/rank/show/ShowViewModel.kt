package com.qxy.victory.ui.activity.rank.show


import com.qxy.victory.repository.RankRepository
import com.qxy.victory.ui.activity.rank.BaseRankViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShowViewModel @Inject constructor(
  private val rankRepository: RankRepository
) : BaseRankViewModel(3, rankRepository) {


}