package com.qxy.victory.ui.fragment.follow

import androidx.annotation.MainThread
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.qxy.victory.model.Follower
import com.qxy.victory.repository.FollowerRepository
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber
import javax.inject.Inject

open class BaseFollowViewModel @Inject constructor(
  private var type: Int,
  private val followerRepository: FollowerRepository
) : BindingViewModel() {
  init {
    Timber.d("init BaseFollowViewModel")
  }

  @get:Bindable
  var isLoading: Boolean by bindingProperty(false)
    private set

  @get:Bindable
  var isLast: Boolean by bindingProperty(false)
    private set

  @get:Bindable
  var isRefresh: Boolean by bindingProperty(false)
    private set

  @get:Bindable
  var toastMessage: String? by bindingProperty(null)
    private set
  private var cursor = 0

  private val pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(cursor)
  private val pokemonListFlow = pokemonFetchingIndex.flatMapLatest { page ->
    followerRepository.fetchFollowerList(
      page = page,
      cursor = cursor,
      type = type,
      onStart = {
        isLoading = true
        isRefresh = false
      },
      onError = {
        toastMessage = it
        isRefresh = true
      },
      onComplete = {
        cursor = it
        if (cursor == -1) {
          isLast = true
        }
        isLoading = false
      },
    )
  }


  @get:Bindable
  val followerList: List<Follower> by pokemonListFlow.asBindingProperty(
    viewModelScope,
    emptyList()
  )

  ///  刷新按钮事件
  @MainThread
  fun refreshList() {
    if (!isLoading) {
      // -1情况为刷新之后请求，但实际请求页为0[RankRepository类中修改]
      pokemonFetchingIndex.value = if (pokemonFetchingIndex.value == -1) 0 else -1
    }
  }


  @MainThread
  fun fetchNextPokemonList() {
    if (!isLoading) {
      // -1情况为刷新之后请求，但实际请求页为0
      if (pokemonFetchingIndex.value == -1) {
        pokemonFetchingIndex.value = 1
      } else {
        pokemonFetchingIndex.value++
      }
    }
  }

}