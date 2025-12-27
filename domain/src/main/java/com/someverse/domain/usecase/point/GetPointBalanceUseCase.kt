package com.someverse.domain.usecase.point

import com.someverse.domain.repository.PointRepository

/**
 * 보유 루미 조회 UseCase
 *
 * 현재 사용자가 보유한 루미 개수를 조회합니다.
 * MY 페이지 우측 상단 아이콘에 표시되는 실시간 잔액을 제공합니다.
 */
class GetPointBalanceUseCase(
    private val pointRepository: PointRepository,
) {
    /**
     * 보유 루미 개수를 조회합니다.
     *
     * @return 보유 루미 개수
     */
    suspend operator fun invoke(): Long = pointRepository.getPointBalance()
}
