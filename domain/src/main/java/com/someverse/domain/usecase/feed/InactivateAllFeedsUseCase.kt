package com.someverse.domain.usecase.feed

import com.someverse.domain.repository.FeedRepository
import javax.inject.Inject

/**
 * Inactivate All Feeds Use Case
 * - Single Responsibility: Inactivate all user's feeds (used when user withdraws)
 * - Delegates to FeedRepository
 */
class InactivateAllFeedsUseCase @Inject constructor(
    private val feedRepository: FeedRepository
) {
    /**
     * Inactivate all user's feeds
     * Used when user withdraws from the service
     *
     * @return Result<Boolean> success indicator or failure with error
     */
    suspend operator fun invoke(): Result<Boolean> {
        return feedRepository.inactivateAllFeeds()
    }
}