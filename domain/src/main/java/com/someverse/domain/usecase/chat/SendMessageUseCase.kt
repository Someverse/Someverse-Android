package com.someverse.domain.usecase.chat

import com.someverse.domain.model.ChatMessage
import com.someverse.domain.model.MessageType
import com.someverse.domain.repository.ChatRepository
import javax.inject.Inject

/**
 * Send Message Use Case
 * - Single Responsibility: Handle message sending with validation
 * - Business logic: Validate message content and type before sending
 * - Delegates to ChatRepository
 */
class SendMessageUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    /**
     * Send a message to a chat room
     *
     * @param roomId Target chat room ID
     * @param content Message content
     * @param messageType Type of message (TEXT, etc.)
     * @return Result<ChatMessage> sent message or failure with error
     */
    suspend operator fun invoke(
        roomId: Long,
        content: String,
        messageType: MessageType = MessageType.TEXT
    ): Result<ChatMessage> {
        // Business logic: Validate input
        if (roomId <= 0) {
            return Result.failure(
                IllegalArgumentException("Invalid room ID")
            )
        }

        if (content.isBlank()) {
            return Result.failure(
                IllegalArgumentException("Message content cannot be blank")
            )
        }

        if (content.length > 1000) {
            return Result.failure(
                IllegalArgumentException("Message must be less than 1000 characters")
            )
        }

        // Delegate to repository
        return chatRepository.sendMessage(roomId, content, messageType)
    }
}