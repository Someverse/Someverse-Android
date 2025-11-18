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
 * 영화 카테고리 선택 화면 ViewModel
 */
@HiltViewModel
class SignupMovieCategoryViewModel @Inject constructor(
    // TODO: private val submitMovieCategoryUseCase: SubmitMovieCategoryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignupMovieCategoryUiState())
    val uiState: StateFlow<SignupMovieCategoryUiState> = _uiState.asStateFlow()

    // 카테고리 선택/해제 토글
    fun toggleCategory(category: String) {
        _uiState.update { currentState ->
            val currentSelections = currentState.selectedCategories.toMutableSet()

            if (currentSelections.contains(category)) {
                // 이미 선택된 경우 제거
                currentSelections.remove(category)
            } else {
                // 최대 개수 선택 확인
                if (currentSelections.size < currentState.maxSelections) {
                    currentSelections.add(category)
                } else {
                    // 최대 개수 초과시 오류 메시지 표시
                    return@update currentState.copy(
                        errorMessage = "최대 ${currentState.maxSelections}개까지 선택할 수 있어요."
                    )
                }
            }

            // 오류 메시지 초기화와 함께 결과 반환
            currentState.copy(
                selectedCategories = currentSelections,
                errorMessage = ""
            )
        }
    }

    // 영화 카테고리 제출
    fun submitCategories() {
        val currentState = _uiState.value

        // 최소 선택 개수 검사
        if (currentState.selectedCategories.size < currentState.minSelections) {
            _uiState.update {
                it.copy(errorMessage = "최소 ${currentState.minSelections}개 이상 선택해주세요.")
            }
            return
        }

        // 최대 선택 개수 검사
        if (currentState.selectedCategories.size > currentState.maxSelections) {
            _uiState.update {
                it.copy(errorMessage = "최대 ${currentState.maxSelections}개까지 선택할 수 있어요.")
            }
            return
        }

        _uiState.update { it.copy(isLoading = true, errorMessage = "") }

        // TODO: 실제 UseCase 연결 시 submitMovieCategoryUseCase 호출
        viewModelScope.launch {
            try {
                // API 호출 지연 시뮬레이션
                kotlinx.coroutines.delay(1000)

                // 성공 시뮬레이션
                _uiState.update {
                    it.copy(isLoading = false, canProceed = true)
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message
                            ?: "오류가 발생했습니다."
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