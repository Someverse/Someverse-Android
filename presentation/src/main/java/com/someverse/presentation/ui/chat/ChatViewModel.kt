package com.someverse.presentation.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.someverse.domain.model.Chat
import com.someverse.domain.model.ChatStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Chat ViewModel
 * - Manages chat list UI state
 * - Handles chat list loading
 * - No business logic (delegated to UseCase)
 */
@HiltViewModel
class ChatViewModel @Inject constructor(
    // TODO: Inject GetChatListUseCase when data layer is ready
    // private val getChatListUseCase: GetChatListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    init {
        loadChatList()
    }

    /**
     * Load chat list from repository
     */
    fun loadChatList() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            // TODO: Replace with actual UseCase call
            // val result = getChatListUseCase()
            // result.onSuccess { chatList ->
            //     _uiState.update { it.copy(isLoading = false, chatList = chatList) }
            // }.onFailure { exception ->
            //     _uiState.update { it.copy(isLoading = false, error = exception.message) }
            // }

            // Dummy data for now
            val dummyChats = listOf(
                Chat(
                    roomId = 1,
                    partnerId = 101,
                    partnerNickname = "대기 중인 회원들",
                    partnerProfileImage = null,
                    status = ChatStatus.PENDING,
                    lastMessage = "인사를 기다리는 회원들이 있어요!",
                    lastMessageTime = "2025-11-24T15:30:00",
                    unreadCount = 99,
                    isRequester = false,
                    lumiUsed = 0,
                    isFreeChat = true
                ),
                Chat(
                    roomId = 2,
                    partnerId = 102,
                    partnerNickname = "마포구보안관2",
                    partnerProfileImage = null,
                    status = ChatStatus.ACTIVE,
                    lastMessage = "동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라 만세",
                    lastMessageTime = "2025-11-24T15:28:00",
                    unreadCount = 99,
                    isRequester = true,
                    lumiUsed = 100,
                    isFreeChat = false
                ),
                Chat(
                    roomId = 3,
                    partnerId = 103,
                    partnerNickname = "마포구보안관2",
                    partnerProfileImage = null,
                    status = ChatStatus.ACTIVE,
                    lastMessage = "동해물과 백두산이 마르고 닳도록 하느님...",
                    lastMessageTime = "2025-11-24T15:25:00",
                    unreadCount = 1,
                    isRequester = false,
                    lumiUsed = 50,
                    isFreeChat = false
                ),
                Chat(
                    roomId = 4,
                    partnerId = 104,
                    partnerNickname = "마포구보안관2",
                    partnerProfileImage = null,
                    status = ChatStatus.ACTIVE,
                    lastMessage = "동해물과 백두산이 마르고 닳도록 하느님...",
                    lastMessageTime = "2025-11-24T15:20:00",
                    unreadCount = 1,
                    isRequester = true,
                    lumiUsed = 0,
                    isFreeChat = true
                ),
                Chat(
                    roomId = 5,
                    partnerId = 105,
                    partnerNickname = "마포구보안관2",
                    partnerProfileImage = null,
                    status = ChatStatus.ACTIVE,
                    lastMessage = "동해물과 백두산이 마르고 닳도록 하느님...",
                    lastMessageTime = "2025-11-24T15:15:00",
                    unreadCount = 1,
                    isRequester = false,
                    lumiUsed = 75,
                    isFreeChat = false
                ),
                Chat(
                    roomId = 6,
                    partnerId = 106,
                    partnerNickname = "마포구보안관2",
                    partnerProfileImage = null,
                    status = ChatStatus.ACTIVE,
                    lastMessage = "동해물과 백두산이 마르고 닳도록 하느님...",
                    lastMessageTime = "2025-11-24T15:10:00",
                    unreadCount = 1,
                    isRequester = true,
                    lumiUsed = 0,
                    isFreeChat = true
                ),
                Chat(
                    roomId = 7,
                    partnerId = 107,
                    partnerNickname = "마포구보안관2",
                    partnerProfileImage = null,
                    status = ChatStatus.ACTIVE,
                    lastMessage = "동해물과 백두산이 마르고 닳도록 하느님...",
                    lastMessageTime = "2025-11-24T15:05:00",
                    unreadCount = 1,
                    isRequester = false,
                    lumiUsed = 50,
                    isFreeChat = false
                )
            )

            _uiState.update { it.copy(isLoading = false, chatList = dummyChats) }
        }
    }

    /**
     * Refresh chat list
     */
    fun refresh() {
        loadChatList()
    }
}