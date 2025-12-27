package com.someverse.presentation.ui.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 영화 취향 선택 화면 ViewModel
 */
@HiltViewModel
class SignupMovieTasteViewModel
    @Inject
    constructor(
        // TODO: private val submitMoviePreferencesUseCase: SubmitMoviePreferencesUseCase
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(SignupMovieTasteUiState())
        val uiState: StateFlow<SignupMovieTasteUiState> = _uiState.asStateFlow()

        // 영화 선택/해제 토글
        fun toggleMovie(movieId: String) {
            _uiState.update { currentState ->
                val currentSelections = currentState.selectedMovies.toMutableSet()

                if (currentSelections.contains(movieId)) {
                    currentSelections.remove(movieId)
                } else {
                    currentSelections.add(movieId)
                }

                currentState.copy(selectedMovies = currentSelections)
            }
        }

        // 영화 선택 제출
        fun submitMovies() {
            val currentState = _uiState.value

            if (currentState.selectedMovies.size < currentState.minSelections) {
                _uiState.update {
                    it.copy(errorMessage = "최소 ${currentState.minSelections}개 이상의 영화를 골라주세요!")
                }
                return
            }

            _uiState.update { it.copy(isLoading = true, errorMessage = "") }

            // TODO: 실제 UseCase 연결 시 submitMoviePreferencesUseCase 호출
            viewModelScope.launch {
                try {
                    // Mocking API call delay
                    kotlinx.coroutines.delay(1000)

                    // Simulation of API success
                    _uiState.update {
                        it.copy(isLoading = false, canProceed = true)
                    }
                } catch (e: Exception) {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = e.message ?: "오류가 발생했습니다.",
                        )
                    }
                }
            }
        }

        // 추가 작업 후 canProceed 상태 초기화
        fun resetProceedState() {
            _uiState.update { it.copy(canProceed = false) }
        }
    }
