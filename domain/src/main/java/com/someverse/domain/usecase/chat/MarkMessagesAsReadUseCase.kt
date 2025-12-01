package com.someverse.domain.usecase.chat

import com.someverse.domain.repository.ChatRepository
import javax.inject.Inject

/**
 * Mark Messages As Read Use Case
 * - Single Responsibility: Mark all messages in a chat room as read
 * - Business logic: Validate room ID
 * - Delegates to ChatRepository
 */
class MarkMessagesAsReadUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    /**
     * Mark all messages in a chat room as read
     *
     * @param roomId Target chat room ID
     * @return Result<Boolean> success status or failure with error
     */
    suspend operator fun invoke(roomId: Long): Result<Boolean> {
        // Business logic: Validate input
        if (roomId <= 0) {
            return Result.failure(
                IllegalArgumentException("Invalid room ID")
            )
        }

        // Delegate to repository
        return chatRepository.markMessagesAsRead(roomId)
    }
}