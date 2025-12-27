package com.someverse.domain.usecase.feed

import com.someverse.domain.model.Feed
import com.someverse.domain.repository.FeedRepository
import javax.inject.Inject

/**
 * Get Random Feeds Use Case
 * - Single Responsibility: Retrieve random feeds
 * - Delegates to FeedRepository
 */
class GetRandomFeedsUseCase
    @Inject
    constructor(
        private val feedRepository: FeedRepository,
    ) {
        /**
         * Get random feeds
         *
         * @return Result<List<Feed>> list of random feeds
         */
        suspend operator fun invoke(): Result<List<Feed>> = feedRepository.getRandomFeeds()
    }
