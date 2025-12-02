package com.someverse.domain.usecase.feed

import com.someverse.domain.repository.FeedRepository
import javax.inject.Inject

/**
 * Inactivate Feed Use Case
 * - Single Responsibility: Inactivate (soft delete) a feed
 * - Business logic: Validate feed ID
 * - Delegates to FeedRepository
 */
class InactivateFeedUseCase @Inject constructor(
    private val feedRepository: FeedRepository
) {
    /**
     * Inactivate a feed (soft delete)
     *
     * @param feedId Feed ID to inactivate
     * @return Result<Boolean> success indicator or failure with error
     */
    suspend operator fun invoke(feedId: Long): Result<Boolean> {
        // Business logic: Validate feed ID
        if (feedId <= 0) {
            return Result.failure(
                IllegalArgumentException("Invalid feed ID")
            )
        }

        // Delegate to repository
        return feedRepository.inactivateFeed(feedId)
    }
}