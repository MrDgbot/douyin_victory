/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qxy.victory.ui.activity.auth


import androidx.annotation.MainThread
import androidx.databinding.Bindable
import com.qxy.victory.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber

@HiltViewModel
class AuthViewModel @Inject constructor(
  private val authRepository: AuthRepository
) : BindingViewModel() {

  init {
    Timber.d("init BaseRankViewModel")
  }

  @get:Bindable
  var isLoading: Boolean by bindingProperty(false)

  @get:Bindable
  var isRefresh: Boolean by bindingProperty(false)
    private set

  @get:Bindable
  var toastMessage: String? by bindingProperty(null)
    private set

  private val flowIndex: MutableStateFlow<Int> = MutableStateFlow(0)
  private val pokemonListFlow = flowIndex.flatMapLatest { page ->
    authRepository.getToken(
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
  val tips: String by pokemonListFlow.asBindingProperty("")

  ///  刷新按钮事件
  @MainThread
  fun refreshList() {
    if (!isLoading) {
      // -1情况为刷新之后请求，但实际请求页为0[RankRepository类中修改]
      flowIndex.value = if (flowIndex.value == -1) 0 else -1
    }
  }


  @MainThread
  fun fetchNextPokemonList() {
    if (isLoading) {
      Timber.d("fetchNextPokemonList")
      // -1情况为刷新之后请求，但实际请求页为0
      if (flowIndex.value == -1) {
        flowIndex.value = 1
      } else {
        flowIndex.value++
      }
    }
  }

}

