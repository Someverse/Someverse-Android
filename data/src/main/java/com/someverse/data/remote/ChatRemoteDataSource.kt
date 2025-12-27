package com.someverse.data.remote

import com.someverse.data.model.ChatEntity
import com.someverse.data.model.ChatMessageEntity
import com.someverse.data.model.ChatMessageHistoryEntity
import com.someverse.data.remote.api.ChatApiService
import com.someverse.data.source.ChatDataSource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Chat Remote DataSource (Real API Implementation)
 * - Implements ChatDataSource interface
 * - TODO: Implement when backend API is ready
 * - Currently throws NotImplementedError
 * - Use ChatLocalDataSource for local development
 */
@Singleton
class ChatRemoteDataSource
    @Inject
    constructor(
        private val chatApiService: ChatApiService,
    ) : ChatDataSource {
        // ==================== Chat Room Management ====================

        override suspend fun requestChat(
            userId: Int,
            message: String,
        ): ChatEntity {
            // TODO: Implement API call
            // Example: chatApiService.requestChat(RequestChatDto(userId, message))
            throw NotImplementedError("Remote API not implemented yet. Use ChatLocalDataSource.")
        }

        override suspend fun getChatList(): List<ChatEntity> {
            // TODO: Implement API call
            // Example: chatApiService.getChatList()
            throw NotImplementedError("Remote API not implemented yet. Use ChatLocalDataSource.")
        }

        override suspend fun getChatDetail(roomId: Int): ChatEntity {
            // TODO: Implement API call
            // Example: chatApiService.getChatDetail(roomId)
            throw NotImplementedError("Remote API not implemented yet. Use ChatLocalDataSource.")
        }

        override suspend fun leaveChat(roomId: Int): Boolean {
            // TODO: Implement API call
            // Example: chatApiService.leaveChat(roomId)
            throw NotImplementedError("Remote API not implemented yet. Use ChatLocalDataSource.")
        }

        override suspend fun deleteChat(roomId: Int): Boolean {
            // TODO: Implement API call
            // Example: chatApiService.deleteChat(roomId)
            throw NotImplementedError("Remote API not implemented yet. Use ChatLocalDataSource.")
        }

        override suspend fun getFreeChatCount(): Int {
            // TODO: Implement API call
            // Example: chatApiService.getFreeChatCount()
            throw NotImplementedError("Remote API not implemented yet. Use ChatLocalDataSource.")
        }

        override suspend fun getUnreadMessageCount(): Int {
            // TODO: Implement API call
            // Example: chatApiService.getUnreadMessageCount()
            throw NotImplementedError("Remote API not implemented yet. Use ChatLocalDataSource.")
        }

        // ==================== Chat Messages ====================

        override suspend fun sendMessage(
            roomId: Long,
            content: String,
            messageType: String,
        ): ChatMessageEntity {
            // TODO: Implement API call
            // Example: chatApiService.sendMessage(roomId, SendMessageDto(content, messageType))
            throw NotImplementedError("Remote API not implemented yet. Use ChatLocalDataSource.")
        }

        override suspend fun getMessageHistory(
            roomId: Long,
            page: Int,
            size: Int,
        ): ChatMessageHistoryEntity {
            // TODO: Implement API call
            // Example: chatApiService.getMessageHistory(roomId, page, size)
            throw NotImplementedError("Remote API not implemented yet. Use ChatLocalDataSource.")
        }

        override suspend fun markMessagesAsRead(roomId: Long): Boolean {
            // TODO: Implement API call
            // Example: chatApiService.markMessagesAsRead(roomId)
            throw NotImplementedError("Remote API not implemented yet. Use ChatLocalDataSource.")
        }

        override suspend fun getUnreadMessageCountForRoom(roomId: Long): Int {
            // TODO: Implement API call
            // Example: chatApiService.getUnreadMessageCountForRoom(roomId)
            throw NotImplementedError("Remote API not implemented yet. Use ChatLocalDataSource.")
        }

        override suspend fun enterChatRoom(roomId: Long): Boolean {
            // TODO: Implement API call
            // Example: chatApiService.enterChatRoom(roomId)
            throw NotImplementedError("Remote API not implemented yet. Use ChatLocalDataSource.")
        }
    }
