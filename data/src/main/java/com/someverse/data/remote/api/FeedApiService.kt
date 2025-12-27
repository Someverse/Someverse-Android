package com.someverse.data.remote.api

import com.someverse.data.model.CreateFeedRequestDto
import com.someverse.data.model.FeedApiResponse
import com.someverse.data.model.FeedEntity
import com.someverse.data.model.UpdateFeedRequestDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

/**
 * Feed API Service Interface
 * - Defines Retrofit API endpoints for feed functionality
 */
interface FeedApiService {
    /**
     * Create feed
     * POST /feed/created
     */
    @POST("feed/created")
    suspend fun createFeed(
        @Body request: CreateFeedRequestDto,
    ): FeedApiResponse<FeedEntity>

    /**
     * Get random feeds
     * GET /feed/random
     */
    @GET("feed/random")
    suspend fun getRandomFeeds(): FeedApiResponse<List<FeedEntity>>

    /**
     * Get my feeds
     * GET /feed/my
     */
    @GET("feed/my")
    suspend fun getMyFeeds(): FeedApiResponse<List<FeedEntity>>

    /**
     * Get feed detail by ID
     * GET /feed/{feedId}
     */
    @GET("feed/{feedId}")
    suspend fun getFeedDetail(
        @Path("feedId") feedId: Long,
    ): FeedApiResponse<FeedEntity>

    /**
     * Update feed
     * PUT /feed/{feedId}/update
     */
    @PUT("feed/{feedId}/update")
    suspend fun updateFeed(
        @Path("feedId") feedId: Long,
        @Body request: UpdateFeedRequestDto,
    ): FeedApiResponse<FeedEntity>

    /**
     * Inactivate feed
     * DELETE /feed/{feedId}/inActive
     */
    @DELETE("feed/{feedId}/inActive")
    suspend fun inactivateFeed(
        @Path("feedId") feedId: Long,
    ): FeedApiResponse<Unit>

    /**
     * Activate feed
     * PUT /feed/{feedId}/active
     */
    @PUT("feed/{feedId}/active")
    suspend fun activateFeed(
        @Path("feedId") feedId: Long,
    ): FeedApiResponse<FeedEntity>

    /**
     * Inactivate all feeds (when user withdraws)
     * DELETE /feed/all/withDraw
     */
    @DELETE("feed/all/withDraw")
    suspend fun inactivateAllFeeds(): FeedApiResponse<Unit>
}
