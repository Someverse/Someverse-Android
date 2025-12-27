package com.someverse.data.remote

import com.someverse.data.model.CreateFeedRequestDto
import com.someverse.data.model.FeedEntity
import com.someverse.data.model.UpdateFeedRequestDto
import com.someverse.data.remote.api.FeedApiService
import com.someverse.data.source.FeedDataSource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Feed Remote DataSource (Real API Implementation)
 * - Implements FeedDataSource interface
 * - Handles API communication for feed operations
 */
@Singleton
class FeedRemoteDataSource
    @Inject
    constructor(
        private val feedApiService: FeedApiService,
    ) : FeedDataSource {
        override suspend fun createFeed(
            feedType: String,
            movieId: Long?,
            musicId: Long?,
            content: String,
        ): FeedEntity {
            val request =
                CreateFeedRequestDto(
                    feedType = feedType,
                    movieId = movieId,
                    musicId = musicId,
                    content = content,
                )
            val response = feedApiService.createFeed(request)

            if (response.success && response.data != null) {
                return response.data
            } else {
                throw Exception(response.message)
            }
        }

        override suspend fun getRandomFeeds(): List<FeedEntity> {
            val response = feedApiService.getRandomFeeds()

            if (response.success && response.data != null) {
                return response.data
            } else {
                throw Exception(response.message)
            }
        }

        override suspend fun getMyFeeds(): List<FeedEntity> {
            val response = feedApiService.getMyFeeds()

            if (response.success && response.data != null) {
                return response.data
            } else {
                throw Exception(response.message)
            }
        }

        override suspend fun getFeedDetail(feedId: Long): FeedEntity {
            val response = feedApiService.getFeedDetail(feedId)

            if (response.success && response.data != null) {
                return response.data
            } else {
                throw Exception(response.message)
            }
        }

        override suspend fun updateFeed(
            feedId: Long,
            content: String,
        ): FeedEntity {
            val request = UpdateFeedRequestDto(content = content)
            val response = feedApiService.updateFeed(feedId, request)

            if (response.success && response.data != null) {
                return response.data
            } else {
                throw Exception(response.message)
            }
        }

        override suspend fun inactivateFeed(feedId: Long): Boolean {
            val response = feedApiService.inactivateFeed(feedId)
            return response.success
        }

        override suspend fun activateFeed(feedId: Long): FeedEntity {
            val response = feedApiService.activateFeed(feedId)

            if (response.success && response.data != null) {
                return response.data
            } else {
                throw Exception(response.message)
            }
        }

        override suspend fun inactivateAllFeeds(): Boolean {
            val response = feedApiService.inactivateAllFeeds()
            return response.success
        }
    }
