package com.someverse.domain.usecase.feed

import com.someverse.domain.model.Feed
import com.someverse.domain.repository.FeedRepository
import javax.inject.Inject

/**
 * Update Feed Use Case
 * - Single Responsibility: Update feed content with validation
 * - Business logic: Validate feed ID and content length
 * - Delegates to FeedRepository
 */
class UpdateFeedUseCase
    @Inject
    constructor(
        private val feedRepository: FeedRepository,
    ) {
        companion object {
            private const val MIN_CONTENT_LENGTH = 15
            private const val MAX_CONTENT_LENGTH = 50
        }

        /**
         * Update feed content
         *
         * @param feedId Feed ID to update
         * @param content New content
         * @return Result<Feed> updated feed or failure with error
         */
        suspend operator fun invoke(
            feedId: Long,
            content: String,
        ): Result<Feed> {
            // Business logic: Validate feed ID
            if (feedId <= 0) {
                return Result.failure(
                    IllegalArgumentException("Invalid feed ID"),
                )
            }

            // Business logic: Validate content
            if (content.isBlank()) {
                return Result.failure(
                    IllegalArgumentException("내용은 필수입니다"),
                )
            }

            if (content.length < MIN_CONTENT_LENGTH || content.length > MAX_CONTENT_LENGTH) {
                return Result.failure(
                    IllegalArgumentException("리뷰는 ${MIN_CONTENT_LENGTH}자 이상 ${MAX_CONTENT_LENGTH}자 이하로 작성해주세요"),
                )
            }

            // Delegate to repository
            return feedRepository.updateFeed(feedId, content)
        }
    }
