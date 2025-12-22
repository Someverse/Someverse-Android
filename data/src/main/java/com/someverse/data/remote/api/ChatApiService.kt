package com.someverse.data.remote.api

/**
 * Chat API Service Interface
 * - Defines Retrofit API endpoints for chat functionality
 * - TODO: Implement when backend API is ready
 * - Add @GET, @POST, etc. annotations
 */
interface ChatApiService {
    // TODO: Define API endpoints
    // Example:
    // @GET("chats")
    // suspend fun getChatList(): List<ChatEntity>

    // @POST("chats/request")
    // suspend fun requestChat(@Body request: RequestChatDto): ChatEntity

    // @GET("chats/{roomId}/messages")
    // suspend fun getMessageHistory(
    //     @Path("roomId") roomId: Long,
    //     @Query("page") page: Int,
    //     @Query("size") size: Int
    // ): ChatMessageHistoryEntity
}