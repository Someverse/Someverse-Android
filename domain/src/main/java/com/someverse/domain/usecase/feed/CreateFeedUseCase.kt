package com.someverse.domain.usecase.feed

import com.someverse.domain.model.Feed
import com.someverse.domain.model.FeedType
import com.someverse.domain.repository.FeedRepository
import javax.inject.Inject

/**
 * Create Feed Use Case
 * - Single Responsibility: Create new feed with validation
 * - Business logic: Validate content length and required IDs based on feed type
 * - Delegates to FeedRepository
 */
class CreateFeedUseCase @Inject constructor(
    private val feedRepository: FeedRepository
) {
    companion object {
        private const val MIN_CONTENT_LENGTH = 15
        private const val MAX_CONTENT_LENGTH = 50
    }

    /**
     * Create a new feed
     *
     * @param feedType Type of feed (MOVIE or MUSIC)
     * @param movieId Movie ID (required if feedType is MOVIE)
     * @param musicId Music ID (required if feedType is MUSIC)
     * @param content Feed content
     * @return Result<Feed> created feed or failure with error
     */
    suspend operator fun invoke(
        feedType: FeedType,
        movieId: Long?,
        musicId: Long?,
        content: String
    ): Result<Feed> {
        // Business logic: Validate content length
        if (content.isBlank()) {
            return Result.failure(
                IllegalArgumentException("내용은 필수입니다")
            )
        }

        if (content.length < MIN_CONTENT_LENGTH || content.length > MAX_CONTENT_LENGTH) {
            return Result.failure(
                IllegalArgumentException("리뷰는 ${MIN_CONTENT_LENGTH}자 이상 ${MAX_CONTENT_LENGTH}자 이하로 작성해주세요")
            )
        }

        // Business logic: Validate required IDs based on feed type
        when (feedType) {
            FeedType.MOVIE -> {
                if (movieId == null) {
                    return Result.failure(
                        IllegalArgumentException("movieId는 필수입니다")
                    )
                }
            }

            FeedType.MUSIC -> {
                if (musicId == null) {
                    return Result.failure(
                        IllegalArgumentException("musicId는 필수입니다")
                    )
                }
            }
        }

        // Delegate to repository
        return feedRepository.createFeed(feedType, movieId, musicId, content)
    }
}