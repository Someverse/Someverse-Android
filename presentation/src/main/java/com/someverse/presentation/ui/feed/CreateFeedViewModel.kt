package com.someverse.presentation.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.someverse.domain.model.FeedType
import com.someverse.domain.usecase.feed.CreateFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Create Feed UI State
 */
data class CreateFeedUiState(
    val content: String = "",
    val selectedMovieId: Long? = null,
    val selectedMovieTitle: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false,
    val isSaveEnabled: Boolean = false,
)

/**
 * Create Feed ViewModel
 * - Manages create feed screen state
 * - Uses CreateFeedUseCase for feed creation
 * - No business logic (delegates to UseCase)
 */
@HiltViewModel
class CreateFeedViewModel
    @Inject
    constructor(
        private val createFeedUseCase: CreateFeedUseCase,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(CreateFeedUiState())
        val uiState: StateFlow<CreateFeedUiState> = _uiState.asStateFlow()

        /**
         * Update content
         */
        fun onContentChange(content: String) {
            _uiState.update {
                it.copy(
                    content = content,
                    isSaveEnabled = content.isNotBlank() && it.selectedMovieId != null,
                )
            }
        }

        /**
         * Select movie
         */
        fun onMovieSelected(
            movieId: Long,
            movieTitle: String,
        ) {
            _uiState.update {
                it.copy(
                    selectedMovieId = movieId,
                    selectedMovieTitle = movieTitle,
                    isSaveEnabled = it.content.isNotBlank(),
                )
            }
        }

        /**
         * Save feed
         */
        fun onSaveClick() {
            val currentState = _uiState.value
            if (currentState.selectedMovieId == null) {
                _uiState.update { it.copy(error = "영화를 선택해주세요") }
                return
            }

            viewModelScope.launch {
                _uiState.update { it.copy(isLoading = true, error = null) }

                val result =
                    createFeedUseCase(
                        feedType = FeedType.MOVIE,
                        movieId = currentState.selectedMovieId,
                        musicId = null,
                        content = currentState.content,
                    )

                result.fold(
                    onSuccess = {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isSuccess = true,
                            )
                        }
                    },
                    onFailure = { error ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = error.message ?: "피드 생성에 실패했습니다",
                            )
                        }
                    },
                )
            }
        }

        /**
         * Clear error
         */
        fun clearError() {
            _uiState.update { it.copy(error = null) }
        }

        /**
         * Reset success state
         */
        fun resetSuccess() {
            _uiState.update { it.copy(isSuccess = false) }
        }
    }
