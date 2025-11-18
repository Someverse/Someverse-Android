package com.someverse.presentation.ui.auth.signup

/**
 * 영화 카테고리 선택 화면 UI 상태
 * 추후 하드코딩 제거 예정
 */
data class SignupMovieCategoryUiState(
    val selectedCategories: Set<String> = emptySet(),
    val movieCategories: List<String> = listOf(
        "뮤지컬", "공포/호러", "애니메이션", "로맨스", "다큐멘터리",
        "스릴러/범죄", "코미디", "SF", "전쟁/사극", "판타지/모험",
        "드라마", "액션"
    ),
    val minSelections: Int = 1,
    val maxSelections: Int = 5,
    val isLoading: Boolean = false,
    val canProceed: Boolean = false,
    val errorMessage: String = ""
) {
    /**
     * 다음 버튼 활성화 여부 - 최소 1개 이상, 최대 5개 이하 선택해야 함
     */
    val isNextEnabled: Boolean
        get() = selectedCategories.size in minSelections..maxSelections &&
                !isLoading
}