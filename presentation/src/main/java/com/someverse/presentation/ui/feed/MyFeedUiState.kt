package com.someverse.presentation.ui.feed

import com.someverse.domain.model.Feed

data class MyFeedUiState(
    val isLoading: Boolean = false,
    val feeds: List<Feed> = emptyList(),
    val error: String? = null,
)