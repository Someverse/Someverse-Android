package com.someverse.presentation.ui.chat

import com.someverse.domain.model.Chat

/**
 * Chat UI State
 * - Represents the state of chat list screen
 */
data class ChatUiState(
    val isLoading: Boolean = false,
    val chatList: List<Chat> = emptyList(),
    val error: String? = null
)