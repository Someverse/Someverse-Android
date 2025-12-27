package com.someverse.data.local

import com.someverse.data.model.ChatEntity
import com.someverse.data.model.ChatMessageEntity
import com.someverse.data.model.ChatMessageHistoryEntity
import com.someverse.data.source.ChatDataSource
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Chat Local DataSource (Mock Implementation)
 * - Implements ChatDataSource interface
 * - Handles local/mock data for development
 * - Simulates API delays
 * - Can be replaced with Room database later for offline support
 */
@Singleton
class ChatLocalDataSource
    @Inject
    constructor() : ChatDataSource {
        // In-memory storage for mock data
        private val chatRooms = mutableListOf<ChatEntity>()
        private val messages = mutableMapOf<Long, MutableList<ChatMessageEntity>>()

        init {
            // Initialize with some mock data
            chatRooms.add(
                ChatEntity(
                    roomId = 1,
                    partnerId = 101,
                    partnerNickname = "대기 중인 회원들",
                    partnerProfileImage = null,
                    status = "PENDING",
                    lastMessage = "인사를 기다리는 회원들이 있어요!",
                    lastMessageTime = "2025-11-24T15:30:00",
                    unreadCount = 99,
                    isRequester = false,
                    lumiUsed = 0,
                    isFreeChat = true,
                ),
            )
            chatRooms.add(
                ChatEntity(
                    roomId = 2,
                    partnerId = 102,
                    partnerNickname = "피드 작성하기",
                    partnerProfileImage = null,
                    status = "ACTIVE",
                    lastMessage = "안녕하세요!",
                    lastMessageTime = "2025-11-30T10:00:00",
                    unreadCount = 2,
                    isRequester = true,
                    lumiUsed = 100,
                    isFreeChat = false,
                ),
            )

            // Initialize mock messages for room 2
            messages[2L] =
                mutableListOf(
                    ChatMessageEntity(
                        messageId = 1,
                        roomId = 2L,
                        senderId = 102,
                        senderNickname = "피드 작성하기",
                        content = "안녕하세요",
                        messageType = "TEXT",
                        isRead = true,
                        createdAt = "2025-11-08T10:00:00",
                    ),
                    ChatMessageEntity(
                        messageId = 2,
                        roomId = 2L,
                        senderId = 102,
                        senderNickname = "피드 작성하기",
                        content = "반갑습니다 ^^",
                        messageType = "TEXT",
                        isRead = true,
                        createdAt = "2025-11-08T10:01:00",
                    ),
                    ChatMessageEntity(
                        messageId = 3,
                        roomId = 2L,
                        senderId = 999,
                        senderNickname = "나",
                        content = "처음뵙겠습니다~",
                        messageType = "TEXT",
                        isRead = true,
                        createdAt = "2025-11-08T10:02:00",
                    ),
                    ChatMessageEntity(
                        messageId = 4,
                        roomId = 2L,
                        senderId = 999,
                        senderNickname = "나",
                        content = "^!^",
                        messageType = "TEXT",
                        isRead = true,
                        createdAt = "2025-11-08T10:02:30",
                    ),
                )
        }

        // ==================== Chat Room Management ====================

        override suspend fun requestChat(
            userId: Int,
            message: String,
        ): ChatEntity {
            delay(500) // Simulate network delay

            val newChat =
                ChatEntity(
                    roomId = chatRooms.size + 1,
                    partnerId = userId,
                    partnerNickname = "User $userId",
                    partnerProfileImage = null,
                    status = "ACTIVE",
                    lastMessage = message,
                    lastMessageTime = getCurrentTimestamp(),
                    unreadCount = 0,
                    isRequester = true,
                    lumiUsed = 50,
                    isFreeChat = false,
                )

            chatRooms.add(newChat)
            println("💬 Local: Chat requested with user $userId")
            return newChat
        }

        override suspend fun getChatList(): List<ChatEntity> {
            delay(500)
            println("💬 Local: Fetched ${chatRooms.size} chat rooms")
            return chatRooms.toList()
        }

        override suspend fun getChatDetail(roomId: Int): ChatEntity {
            delay(300)
            val chat =
                chatRooms.find { it.roomId == roomId }
                    ?: throw NoSuchElementException("Chat room $roomId not found")
            println("💬 Local: Fetched chat detail for room $roomId")
            return chat
        }

        override suspend fun leaveChat(roomId: Int): Boolean {
            delay(300)
            val removed = chatRooms.removeIf { it.roomId == roomId }
            println("💬 Local: Left chat room $roomId - Success: $removed")
            return removed
        }

        override suspend fun deleteChat(roomId: Int): Boolean {
            delay(300)
            val removed = chatRooms.removeIf { it.roomId == roomId }
            messages.remove(roomId.toLong())
            println("💬 Local: Deleted chat room $roomId - Success: $removed")
            return removed
        }

        override suspend fun getFreeChatCount(): Int {
            delay(200)
            val count = 3 // Mock: 3 free chats available
            println("💬 Local: Free chat count: $count")
            return count
        }

        override suspend fun getUnreadMessageCount(): Int {
            delay(200)
            val count = 10
            println("💬 Local: Unread count for all: $count")
            return count
        }

        // ==================== Chat Messages ====================

        override suspend fun sendMessage(
            roomId: Long,
            content: String,
            messageType: String,
        ): ChatMessageEntity {
            delay(500)

            val roomMessages = messages.getOrPut(roomId) { mutableListOf() }
            val newMessage =
                ChatMessageEntity(
                    messageId = System.currentTimeMillis(),
                    roomId = roomId,
                    senderId = 999, // Current user ID
                    senderNickname = "나",
                    content = content,
                    messageType = messageType,
                    isRead = false,
                    createdAt = getCurrentTimestamp(),
                )

            roomMessages.add(newMessage)

            // Update last message in chat room
            val chatIndex = chatRooms.indexOfFirst { it.roomId == roomId.toInt() }
            if (chatIndex != -1) {
                chatRooms[chatIndex] =
                    chatRooms[chatIndex].copy(
                        lastMessage = content,
                        lastMessageTime = newMessage.createdAt,
                    )
            }

            println("💬 Local: Message sent to room $roomId")
            return newMessage
        }

        override suspend fun getMessageHistory(
            roomId: Long,
            page: Int,
            size: Int,
        ): ChatMessageHistoryEntity {
            delay(500)

            val roomMessages = messages[roomId] ?: emptyList()
            val totalElements = roomMessages.size
            val totalPages = (totalElements + size - 1) / size
            val startIndex = page * size
            val endIndex = minOf(startIndex + size, totalElements)

            val pageMessages =
                if (startIndex < totalElements) {
                    roomMessages.subList(startIndex, endIndex)
                } else {
                    emptyList()
                }

            println("💬 Local: Fetched ${pageMessages.size} messages for room $roomId (page $page)")

            return ChatMessageHistoryEntity(
                messages = pageMessages,
                currentPage = page,
                totalPages = totalPages,
                totalElements = totalElements,
                hasNext = (page + 1) < totalPages,
            )
        }

        override suspend fun markMessagesAsRead(roomId: Long): Boolean {
            delay(300)

            messages[roomId]?.forEach { message ->
                if (!message.isRead) {
                    val index = messages[roomId]!!.indexOf(message)
                    messages[roomId]!![index] = message.copy(isRead = true)
                }
            }

            // Update unread count in chat room
            val chatIndex = chatRooms.indexOfFirst { it.roomId == roomId.toInt() }
            if (chatIndex != -1) {
                chatRooms[chatIndex] = chatRooms[chatIndex].copy(unreadCount = 0)
            }

            println("💬 Local: Marked messages as read for room $roomId")
            return true
        }

        override suspend fun getUnreadMessageCountForRoom(roomId: Long): Int {
            delay(200)
            val count = messages[roomId]?.count { !it.isRead } ?: 0
            println("💬 Local: Unread message count for room $roomId: $count")
            return count
        }

        override suspend fun enterChatRoom(roomId: Long): Boolean {
            delay(300)
            println("💬 Local: Entered chat room $roomId")
            return true
        }

        // ==================== Helper Methods ====================

        private fun getCurrentTimestamp(): String =
            java.time.LocalDateTime
                .now()
                .toString()
    }
