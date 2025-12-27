package com.someverse.domain.usecase.feed

import com.someverse.domain.model.Feed
import com.someverse.domain.repository.FeedRepository
import javax.inject.Inject

/**
 * Get My Feeds Use Case
 * - Single Responsibility: Retrieve all feeds created by current user
 * - Business logic: Sort feeds by ID (most recent first)
 * - Delegates to FeedRepository
 */
class GetMyFeedsUseCase
    @Inject
    constructor(
        private val feedRepository: FeedRepository,
    ) {
        /**
         * Get all feeds created by current user
         *
         * @return Result<List<Feed>> list of user's feeds sorted by ID descending
         */
        suspend operator fun invoke(): Result<List<Feed>> =
            feedRepository.getMyFeeds().map { feeds ->
                // Business logic: Sort by ID (most recent first)
                feeds.sortedByDescending { it.id }
            }
    }
