package com.someverse.data.source

import com.someverse.data.model.ChatEntity
import com.someverse.data.model.ChatMessageEntity
import com.someverse.data.model.ChatMessageHistoryEntity

/**
 * Chat DataSource Interface
 * - Abstract data access operations for chat functionality
 * - Can be implemented by Local (mock) or Remote (API)
 * - Repository depends on this interface, not concrete implementations
 */
interface ChatDataSource {
    // ==================== Chat Room Management ====================

    /**
     * Request a new chat with a user
     */
    suspend fun requestChat(
        userId: Int,
        message: String,
    ): ChatEntity

    /**
     * Get list of all chat rooms for current user
     */
    suspend fun getChatList(): List<ChatEntity>

    /**
     * Get detailed information about a specific chat room
     */
    suspend fun getChatDetail(roomId: Int): ChatEntity

    /**
     * Leave a chat room
     */
    suspend fun leaveChat(roomId: Int): Boolean

    /**
     * Delete a chat room
     */
    suspend fun deleteChat(roomId: Int): Boolean

    /**
     * Get today's free chat count
     */
    suspend fun getFreeChatCount(): Int

    /**
     * Get unread message count for all
     */
    suspend fun getUnreadMessageCount(): Int

    // ==================== Chat Messages ====================

    /**
     * Send a message to a chat room
     */
    suspend fun sendMessage(
        roomId: Long,
        content: String,
        messageType: String,
    ): ChatMessageEntity

    /**
     * Get message history for a chat room with pagination
     */
    suspend fun getMessageHistory(
        roomId: Long,
        page: Int,
        size: Int,
    ): ChatMessageHistoryEntity

    /**
     * Mark all messages in a room as read
     */
    suspend fun markMessagesAsRead(roomId: Long): Boolean

    /**
     * Get unread message count for a specific room
     */
    suspend fun getUnreadMessageCountForRoom(roomId: Long): Int

    /**
     * Enter a chat room (mark as active/online)
     */
    suspend fun enterChatRoom(roomId: Long): Boolean
}
