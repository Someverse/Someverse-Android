package com.someverse.domain.usecase.feed

import com.someverse.domain.model.Feed
import com.someverse.domain.repository.FeedRepository
import javax.inject.Inject

/**
 * Get Feed Detail Use Case
 * - Single Responsibility: Retrieve specific feed detail by ID
 * - Business logic: Validate feed ID
 * - Delegates to FeedRepository
 */
class GetFeedDetailUseCase @Inject constructor(
    private val feedRepository: FeedRepository
) {
    /**
     * Get feed detail by ID
     *
     * @param feedId Feed ID to retrieve
     * @return Result<Feed> feed detail or failure with error
     */
    suspend operator fun invoke(feedId: Long): Result<Feed> {
        // Business logic: Validate feed ID
        if (feedId <= 0) {
            return Result.failure(
                IllegalArgumentException("Invalid feed ID")
            )
        }

        // Delegate to repository
        return feedRepository.getFeedDetail(feedId)
    }
}