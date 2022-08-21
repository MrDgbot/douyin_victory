package com.qxy.victory.ui.fragment.video

import androidx.annotation.MainThread
import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qxy.victory.model.RankItem
import com.qxy.victory.model.VideoData
import com.qxy.victory.repository.VideoRepository
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class VideoViewModel @Inject constructor(
  videoRepository: VideoRepository
) : BindingViewModel(){
    // TODO: Implement the ViewModel
    init {
      Timber.d("init VideoViewModel")
    }

  @get:Bindable
  var isLoading: Boolean by bindingProperty(false)
    private set

  @get:Bindable
  var isRefresh: Boolean by bindingProperty(false)
    private set

  @get:Bindable
  var toastMessage: String? by bindingProperty(null)
    private set

  private val pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
  private val pokemonListFlow = pokemonFetchingIndex.flatMapLatest { page ->
    videoRepository.fetchVideoList(
      onStart = {
        isLoading = true
        isRefresh = false
      },
      onError = {
        toastMessage = it
        isRefresh = true
      },
      onComplete = {
        isLoading = false
      },
    )
  }
  @get:Bindable
  val videoDataList: List<VideoData> by pokemonListFlow.asBindingProperty(
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