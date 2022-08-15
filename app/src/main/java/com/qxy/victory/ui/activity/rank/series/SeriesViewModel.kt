package com.qxy.victory.ui.activity.rank.series


import com.qxy.victory.repository.RankRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


import com.qxy.victory.ui.activity.rank.BaseRankViewModel

@HiltViewModel
class SeriesViewModel @Inject constructor(
  private val rankRepository: RankRepository
) : BaseRankViewModel(2,rankRepository) {


}