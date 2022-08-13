package com.qxy.victory.ui.fragment.tvshow

import androidx.annotation.MainThread
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.qxy.victory.model.ActorItem
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
class TVShowViewModel  @Inject constructor(
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
  val tvShowItemList: List<ActorItem> by pokemonListFlow.asBindingProperty(viewModelScope, emptyList())

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