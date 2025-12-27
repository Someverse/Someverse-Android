package com.someverse.presentation.ui.feed

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

/**
 * Feed Card Custom Shape
 * - 상단: 16dp 라운드
 * - 하단: 16dp 라운드 + 중앙 볼록한 곡선
 */
class FeedCardShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val cornerRadius = with(density) { 38.dp.toPx() }
        val bottomCurveHeight = with(density) { 25.dp.toPx() } // 하단 중앙 볼록 높이 (낮출수록 완만)

        val path =
            Path().apply {
                // 왼쪽 상단 시작
                moveTo(cornerRadius, 0f)

                // 상단 라인
                lineTo(size.width - cornerRadius, 0f)

                // 오른쪽 상단 코너
                arcTo(
                    rect =
                        androidx.compose.ui.geometry.Rect(
                            left = size.width - cornerRadius * 2,
                            top = 0f,
                            right = size.width,
                            bottom = cornerRadius * 2,
                        ),
                    startAngleDegrees = -90f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false,
                )

                // 오른쪽 라인
                lineTo(size.width, size.height - cornerRadius)

                // 오른쪽 하단 코너
                arcTo(
                    rect =
                        androidx.compose.ui.geometry.Rect(
                            left = size.width - cornerRadius * 2,
                            top = size.height - cornerRadius * 2,
                            right = size.width,
                            bottom = size.height,
                        ),
                    startAngleDegrees = 0f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false,
                )

                // 하단 라인 (오른쪽에서 중앙으로 볼록한 곡선)
                // size 높이를 기준으로 볼록하게 나가도록 (Path가 bounds 밖으로)
                val controlPoint1X = size.width * 0.7f
                val controlPoint1Y = size.height
                val controlPoint2X = size.width * 0.6f
                val controlPoint2Y = size.height + (bottomCurveHeight * 0.8f)
                val endPointX = size.width * 0.5f
                val endPointY = size.height + bottomCurveHeight // 가장 나온 지점

                cubicTo(
                    x1 = controlPoint1X,
                    y1 = controlPoint1Y,
                    x2 = controlPoint2X,
                    y2 = controlPoint2Y,
                    x3 = endPointX,
                    y3 = endPointY,
                )

                // 하단 라인 (중앙에서 왼쪽으로 볼록한 곡선)
                cubicTo(
                    x1 = size.width * 0.4f,
                    y1 = size.height + (bottomCurveHeight * 0.8f),
                    x2 = size.width * 0.3f,
                    y2 = size.height,
                    x3 = cornerRadius,
                    y3 = size.height,
                )

                // 왼쪽 하단 코너
                arcTo(
                    rect =
                        androidx.compose.ui.geometry.Rect(
                            left = 0f,
                            top = size.height - cornerRadius * 2,
                            right = cornerRadius * 2,
                            bottom = size.height,
                        ),
                    startAngleDegrees = 90f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false,
                )

                // 왼쪽 라인
                lineTo(0f, cornerRadius)

                // 왼쪽 상단 코너
                arcTo(
                    rect =
                        androidx.compose.ui.geometry.Rect(
                            left = 0f,
                            top = 0f,
                            right = cornerRadius * 2,
                            bottom = cornerRadius * 2,
                        ),
                    startAngleDegrees = 180f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false,
                )

                close()
            }

        return Outline.Generic(path)
    }
}
