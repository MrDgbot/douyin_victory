package com.qxy.victory.ui.fragment.follow

import androidx.annotation.MainThread
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.qxy.victory.model.Follower
import com.qxy.victory.repository.FollowerRepository
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FollowerViewModel @Inject constructor(
  private val followerRepository: FollowerRepository
) : BaseFollowViewModel(followerRepository) {

}