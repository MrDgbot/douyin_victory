package com.qxy.victory.ui.activity.show

import androidx.annotation.MainThread
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.qxy.victory.model.ShowItem
import com.qxy.victory.repository.ShowRepository
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ShowViewModel  @Inject constructor(
  private val showRepository: ShowRepository
) : BindingViewModel() {

  @get:Bindable
  var isLoading: Boolean by bindingProperty(false)
    private set

  @get:Bindable
  var toastMessage: String? by bindingProperty(null)
    private set

  private val pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
  private val pokemonListFlow = pokemonFetchingIndex.flatMapLatest { page ->
    showRepository.fetchShowList(
      page = page,
      onStart = { isLoading = true },
      onComplete = { isLoading = false },
      onError = { toastMessage = it }
    )
  }

  @get:Bindable
  val tvShowItemList: List<ShowItem> by pokemonListFlow.asBindingProperty(viewModelScope, emptyList())

  init {
    Timber.d("init TVViewModel")
  }

  @MainThread
  fun fetchNextPokemonList() {
    if (!isLoading) {
      pokemonFetchingIndex.value++
    }
  }
}