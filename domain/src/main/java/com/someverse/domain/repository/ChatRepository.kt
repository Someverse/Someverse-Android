package com.someverse.domain.repository

import com.someverse.domain.model.Chat
import com.someverse.domain.model.ChatMessage
import com.someverse.domain.model.ChatMessageHistory
import com.someverse.domain.model.MessageType
import com.someverse.domain.model.UnreadCount

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

    // 읽지 않은 메시지 총 갯수 조회
    suspend fun getUnreadMessageCount(): Result<Int>

    // 메시지 전송
    suspend fun sendMessage(
        roomId: Long,
        content: String,
        messageType: MessageType
    ): Result<ChatMessage>

    // 메시지 히스토리 조회 (페이징)
    suspend fun getMessageHistory(
        roomId: Long,
        page: Int = 0,
        size: Int = 50
    ): Result<ChatMessageHistory>

    // 메시지 읽음 처리
    suspend fun markMessagesAsRead(roomId: Long): Result<Boolean>

    // 특정 채팅방 읽지 않은 메시지 개수 조회
    suspend fun getUnreadMessageCountForRoom(roomId: Long): Result<UnreadCount>

    // 채팅방 입장
    suspend fun enterChatRoom(roomId: Long): Result<Boolean>
}