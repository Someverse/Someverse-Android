package com.someverse.domain.usecase.chat

import com.someverse.domain.repository.ChatRepository
import javax.inject.Inject

/**
 * Enter Chat Room Use Case
 * - Single Responsibility: Handle entering a chat room
 * - Business logic: Validate room ID before entering
 * - Delegates to ChatRepository
 */
class EnterChatRoomUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    /**
     * Enter a chat room
     *
     * @param roomId Target chat room ID to enter
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
        return chatRepository.enterChatRoom(roomId)
    }
}