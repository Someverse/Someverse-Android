package com.someverse.domain.usecase.chat

import com.someverse.domain.model.ChatMessageHistory
import com.someverse.domain.repository.ChatRepository
import javax.inject.Inject

/**
 * Get Message History Use Case
 * - Single Responsibility: Retrieve paginated message history
 * - Business logic: Validate pagination parameters
 * - Delegates to ChatRepository
 */
class GetMessageHistoryUseCase
    @Inject
    constructor(
        private val chatRepository: ChatRepository,
    ) {
        /**
         * Get message history for a chat room with pagination
         *
         * @param roomId Target chat room ID
         * @param page Page number (0-based)
         * @param size Number of messages per page
         * @return Result<ChatMessageHistory> paginated message history or failure with error
         */
        suspend operator fun invoke(
            roomId: Long,
            page: Int = 0,
            size: Int = 50,
        ): Result<ChatMessageHistory> {
            // Business logic: Validate input
            if (roomId <= 0) {
                return Result.failure(
                    IllegalArgumentException("Invalid room ID"),
                )
            }

            if (page < 0) {
                return Result.failure(
                    IllegalArgumentException("Page number cannot be negative"),
                )
            }

            // Delegate to repository
            return chatRepository.getMessageHistory(roomId, page, size)
        }
    }
