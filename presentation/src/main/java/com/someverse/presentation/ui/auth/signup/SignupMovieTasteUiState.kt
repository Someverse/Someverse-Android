package com.someverse.presentation.ui.auth.signup

/**
 * 영화 취향 선택 화면 UI 상태
 * 추후 MovieUIModel로 변경, presentation mapper 구현 예정
 */
data class Movie(
    val id: String,
    val title: String,
    val posterUrl: String = "", // 실제로는 이미지 URL이 들어갈 예정
)

data class SignupMovieTasteUiState(
    val selectedMovies: Set<String> = emptySet(), // 선택된 영화 ID 목록
    val movies: List<Movie> =
        listOf(
            Movie("1", "영화 제목"),
            Movie("2", "영화 제목"),
            Movie("3", "영화 제목"),
            Movie("4", "영화 제목"),
            Movie("5", "영화 제목"),
            Movie("6", "영화 제목"),
            Movie("7", "영화 제목"),
            Movie("8", "영화 제목"),
            Movie("9", "영화 제목"),
            Movie("10", "영화 제목"),
            Movie("11", "영화 제목"),
            Movie("12", "영화 제목"),
        ),
    val minSelections: Int = 5, // 최소 5개 선택
    val isLoading: Boolean = false,
    val canProceed: Boolean = false,
    val errorMessage: String = "",
) {
    /**
     * 다음 버튼 활성화 여부 - 최소 5개 이상 선택해야 함
     */
    val isNextEnabled: Boolean
        get() = selectedMovies.size >= minSelections && !isLoading
}
