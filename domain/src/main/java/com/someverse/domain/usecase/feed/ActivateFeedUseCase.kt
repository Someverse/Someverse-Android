package com.someverse.domain.usecase.feed

import com.someverse.domain.model.Feed
import com.someverse.domain.repository.FeedRepository
import javax.inject.Inject

/**
 * Activate Feed Use Case
 * - Single Responsibility: Activate an inactive feed
 * - Business logic: Validate feed ID
 * - Delegates to FeedRepository
 */
class ActivateFeedUseCase @Inject constructor(
    private val feedRepository: FeedRepository
) {
    /**
     * Activate an inactive feed
     *
     * @param feedId Feed ID to activate
     * @return Result<Feed> activated feed or failure with error
     */
    suspend operator fun invoke(feedId: Long): Result<Feed> {
        // Business logic: Validate feed ID
        if (feedId <= 0) {
            return Result.failure(
                IllegalArgumentException("Invalid feed ID")
            )
        }

        // Delegate to repository
        return feedRepository.activateFeed(feedId)
    }
}