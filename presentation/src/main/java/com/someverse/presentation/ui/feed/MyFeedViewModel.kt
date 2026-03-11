package com.someverse.presentation.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.someverse.domain.usecase.feed.GetMyFeedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyFeedViewModel
@Inject
constructor(
    private val getMyFeedsUseCase: GetMyFeedsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(MyFeedUiState())
    val uiState: StateFlow<MyFeedUiState> = _uiState.asStateFlow()

    init {
        loadMyFeeds()
    }

    /**
     * Load random feeds from repository
     */
    fun loadMyFeeds() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            getMyFeedsUseCase()
                .onSuccess { feeds ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            feeds = feeds,
                            error = null,
                        )
                    }
                }.onFailure { exception ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = exception.message ?: "Unknown error occurred",
                        )
                    }
                }
        }
    }

    /**
     * Refresh feeds
     */
    fun refresh() {
        loadMyFeeds()
    }
}
