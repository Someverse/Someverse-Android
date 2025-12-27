package com.someverse.data.impl

import com.someverse.data.mapper.ChatMapper.toDomain
import com.someverse.data.mapper.ChatMapper.toUnreadCount
import com.someverse.data.source.ChatDataSource
import com.someverse.domain.model.Chat
import com.someverse.domain.model.ChatMessage
import com.someverse.domain.model.ChatMessageHistory
import com.someverse.domain.model.MessageType
import com.someverse.domain.model.UnreadCount
import com.someverse.domain.repository.ChatRepository
import javax.inject.Inject

/**
 * Chat Repository Implementation
 * - Implements ChatRepository interface from domain layer
 * - Depends on ChatDataSource interface (not concrete implementation)
 * - Uses Mapper to convert Entity (DTO) to Domain Model
 * - Handles error handling and data transformation
 */
class ChatRepositoryImpl
    @Inject
    constructor(
        private val dataSource: ChatDataSource, // Interface injection!
    ) : ChatRepository {
        override suspend fun requestChat(
            userId: Int,
            message: String,
        ): Result<Chat> =
            try {
                val chatEntity = dataSource.requestChat(userId, message)
                Result.success(chatEntity.toDomain())
            } catch (e: Exception) {
                Result.failure(e)
            }

        override suspend fun getChatList(): Result<List<Chat>> =
            try {
                val chatEntities = dataSource.getChatList()
                Result.success(chatEntities.map { it.toDomain() })
            } catch (e: Exception) {
                Result.failure(e)
            }

        override suspend fun getChatDetail(roomId: Int): Result<Chat> =
            try {
                val chatEntity = dataSource.getChatDetail(roomId)
                Result.success(chatEntity.toDomain())
            } catch (e: Exception) {
                Result.failure(e)
            }

        override suspend fun leaveChat(roomId: Int): Result<Boolean> =
            try {
                val success = dataSource.leaveChat(roomId)
                Result.success(success)
            } catch (e: Exception) {
                Result.failure(e)
            }

        override suspend fun deleteChat(roomId: Int): Result<Boolean> =
            try {
                val success = dataSource.deleteChat(roomId)
                Result.success(success)
            } catch (e: Exception) {
                Result.failure(e)
            }

        override suspend fun getFreeChatCount(): Result<Int> =
            try {
                val count = dataSource.getFreeChatCount()
                Result.success(count)
            } catch (e: Exception) {
                Result.failure(e)
            }

        override suspend fun getUnreadMessageCount(): Result<Int> =
            try {
                val count = dataSource.getUnreadMessageCount()
                Result.success(count)
            } catch (e: Exception) {
                Result.failure(e)
            }

        override suspend fun sendMessage(
            roomId: Long,
            content: String,
            messageType: MessageType,
        ): Result<ChatMessage> =
            try {
                val messageEntity =
                    dataSource.sendMessage(
                        roomId = roomId,
                        content = content,
                        messageType = messageType.name,
                    )
                Result.success(messageEntity.toDomain())
            } catch (e: Exception) {
                Result.failure(e)
            }

        override suspend fun getMessageHistory(
            roomId: Long,
            page: Int,
            size: Int,
        ): Result<ChatMessageHistory> =
            try {
                val historyEntity = dataSource.getMessageHistory(roomId, page, size)
                Result.success(historyEntity.toDomain())
            } catch (e: Exception) {
                Result.failure(e)
            }

        override suspend fun markMessagesAsRead(roomId: Long): Result<Boolean> =
            try {
                val success = dataSource.markMessagesAsRead(roomId)
                Result.success(success)
            } catch (e: Exception) {
                Result.failure(e)
            }

        override suspend fun getUnreadMessageCountForRoom(roomId: Long): Result<UnreadCount> =
            try {
                val count = dataSource.getUnreadMessageCountForRoom(roomId)
                Result.success(count.toUnreadCount())
            } catch (e: Exception) {
                Result.failure(e)
            }

        override suspend fun enterChatRoom(roomId: Long): Result<Boolean> =
            try {
                val success = dataSource.enterChatRoom(roomId)
                Result.success(success)
            } catch (e: Exception) {
                Result.failure(e)
            }
    }
