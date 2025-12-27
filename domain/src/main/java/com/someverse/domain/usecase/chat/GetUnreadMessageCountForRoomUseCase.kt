package com.someverse.domain.usecase.chat

import com.someverse.domain.model.UnreadCount
import com.someverse.domain.repository.ChatRepository
import javax.inject.Inject

/**
 * Get Unread Message Count For Room Use Case
 * - Single Responsibility: Retrieve unread message count for a specific chat room
 * - Business logic: Validate room ID
 * - Delegates to ChatRepository
 */
class GetUnreadMessageCountForRoomUseCase
    @Inject
    constructor(
        private val chatRepository: ChatRepository,
    ) {
        /**
         * Get unread message count for a specific chat room
         *
         * @param roomId Target chat room ID
         * @return Result<UnreadCount> unread count or failure with error
         */
        suspend operator fun invoke(roomId: Long): Result<UnreadCount> {
            // Business logic: Validate input
            if (roomId <= 0) {
                return Result.failure(
                    IllegalArgumentException("Invalid room ID"),
                )
            }

            // Delegate to repository
            return chatRepository.getUnreadMessageCountForRoom(roomId)
        }
    }
