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
  private var refreshID = -2


  /* `fetchingIndex` 是一个`MutableStateFlow`，它是一个代表可变状态的`Flow`。 */
  private val fetchingIndex: MutableStateFlow<Int> = MutableStateFlow(cursor)

  /* `flatMapLatest` 是一个暂停函数，它通过将给定的暂停变换函数应用于每个值来变换流的值。 */
  private val listFlow = fetchingIndex.flatMapLatest { page ->
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
  val followerList: List<Follower> by listFlow.asBindingProperty(
    viewModelScope,
    emptyList()
  )

  ///  刷新按钮事件
  @MainThread
  fun refreshList() {
    if (!isLoading) {
      // -1情况为刷新之后请求，但实际请求页为0[RankRepository类中修改]
      fetchingIndex.value = if (fetchingIndex.value == refreshID) 0 else refreshID
    }
  }


  @MainThread
  fun fetchNextPokemonList() {
    if (!isLoading) {
      // 当页数为刷新标识时，则重置页数为0
      if (fetchingIndex.value == refreshID) {
        fetchingIndex.value = 1
      } else {
        fetchingIndex.value++
      }
    }
  }

}