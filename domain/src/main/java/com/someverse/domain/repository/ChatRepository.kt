package com.someverse.domain.repository

import com.someverse.domain.model.Chat

interface ChatRepository {
    // 채팅 신청
    suspend fun requestChat(userId: Int, message: String): Result<Chat>

    // 내 채팅방 목록 조회
    suspend fun getChatList(): Result<List<Chat>>

    // 채팅방 상세 조히
    suspend fun getChatDetail(roomId: Int): Result<Chat>

    // 채팅방 나가기
    suspend fun leaveChat(roomId: Int): Result<Boolean>

    // 채팅방 삭제
    suspend fun deleteChat(roomId: Int): Result<Boolean>

    // 오늘 무료 채팅 횟수 조회
    suspend fun getFreeChatCount(): Result<Int>

    // 읽지 않은 메시지 갯수 조회
    suspend fun getUnreadMessageCount(): Result<Int>
}