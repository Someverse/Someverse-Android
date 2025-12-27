package com.someverse.domain.repository

/**
 * 포인트(루미) 관련 Repository Interface
 */
interface PointRepository {
    /**
     * 현재 사용자가 보유한 루미 개수를 조회합니다.
     *
     * @return 보유 루미 개수
     */
    suspend fun getPointBalance(): Long
}
