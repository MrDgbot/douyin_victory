package com.qxy.victory.ui.activity.series

import androidx.annotation.MainThread
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.qxy.victory.model.MovieItem
import com.qxy.victory.model.SeriesItem
import com.qxy.victory.repository.SeriesRepository
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class SeriesRankingViewModel @Inject constructor(
  private val seriesRepository: SeriesRepository
) : BindingViewModel() {

  @get:Bindable
  var isLoading: Boolean by bindingProperty(false)
    private set

  @get:Bindable
  var toastMessage: String? by bindingProperty(null)
    private set

  private val pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
  private val pokemonListFlow = pokemonFetchingIndex.flatMapLatest { page ->
    seriesRepository.fetchSeriesList(
      page = page,
      onStart = { isLoading = true },
      onComplete = { isLoading = false },
      onError = { toastMessage = it }
    )
  }


  @get:Bindable
  val seriesItemList: List<SeriesItem> by pokemonListFlow.asBindingProperty(
    viewModelScope,
    emptyList()
  )

  init {
    Timber.d("init SeriesRankingViewModel")
  }

  @get:Bindable
  val pushPage: Int by bindingProperty(0)

  @MainThread
  fun fetchNextPokemonList() {
    if (!isLoading) {
      pokemonFetchingIndex.value++
    }
  }
}