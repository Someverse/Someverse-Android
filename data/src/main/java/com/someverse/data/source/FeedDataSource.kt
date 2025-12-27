package com.someverse.data.source

import com.someverse.data.model.FeedEntity

/**
 * Feed DataSource Interface
 * - Abstract data access operations for feed functionality
 * - Can be implemented by Local (mock) or Remote (API)
 * - Repository depends on this interface, not concrete implementations
 */
interface FeedDataSource {
    /**
     * Create a new feed
     */
    suspend fun createFeed(
        feedType: String,
        movieId: Long?,
        musicId: Long?,
        content: String,
    ): FeedEntity

    /**
     * Get random feeds
     */
    suspend fun getRandomFeeds(): List<FeedEntity>

    /**
     * Get all feeds created by current user
     */
    suspend fun getMyFeeds(): List<FeedEntity>

    /**
     * Get feed detail by ID
     */
    suspend fun getFeedDetail(feedId: Long): FeedEntity

    /**
     * Update feed content
     */
    suspend fun updateFeed(
        feedId: Long,
        content: String,
    ): FeedEntity

    /**
     * Inactivate (soft delete) a feed
     */
    suspend fun inactivateFeed(feedId: Long): Boolean

    /**
     * Activate an inactive feed
     */
    suspend fun activateFeed(feedId: Long): FeedEntity

    /**
     * Inactivate all user's feeds (used when user withdraws)
     */
    suspend fun inactivateAllFeeds(): Boolean
}
