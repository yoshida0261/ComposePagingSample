/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package yoshida0261.compose.sample2022.ui.composepaging3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import yoshida0261.compose.sample2022.data.ComposePaging3Repository
import yoshida0261.compose.sample2022.ui.composepaging3.ComposePaging3UiState.Error
import yoshida0261.compose.sample2022.ui.composepaging3.ComposePaging3UiState.Loading
import yoshida0261.compose.sample2022.ui.composepaging3.ComposePaging3UiState.Success
import javax.inject.Inject

@HiltViewModel
class ComposePaging3ViewModel @Inject constructor(
    private val composePaging3Repository: ComposePaging3Repository
) : ViewModel() {

    val uiState: StateFlow<ComposePaging3UiState> = composePaging3Repository
        .composePaging3s.map(::Success)
        .catch { Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    fun addComposePaging3(name: String) {
        viewModelScope.launch {
            composePaging3Repository.add(name)
        }
    }
}

sealed interface ComposePaging3UiState {
    object Loading : ComposePaging3UiState
    data class Error(val throwable: Throwable) : ComposePaging3UiState
    data class Success(val data: List<String>) : ComposePaging3UiState
}
