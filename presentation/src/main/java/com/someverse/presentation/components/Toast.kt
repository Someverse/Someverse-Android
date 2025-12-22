package com.someverse.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.someverse.presentation.ui.theme.*
import kotlinx.coroutines.delay

/**
 * Toast 메시지를 표시하는 Composable
 * @param message 표시할 메시지
 * @param onDismiss Toast가 사라질 때 호출되는 콜백
 * @param duration Toast 표시 시간 (밀리초)
 * @param isError 에러 메시지 여부 (true: 빨간색, false: 검은색)
 */
@Composable
fun Toast(
    modifier: Modifier = Modifier,
    message: String,
    onDismiss: () -> Unit,
    duration: Long = 3000L,
    isError: Boolean = true
) {
    LaunchedEffect(message) {
        if (message.isNotEmpty()) {
            delay(duration)
            onDismiss()
        }
    }

    if (message.isNotEmpty()) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = Dimensions.space16),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = if (isError) Color(0xFFFF4D4D) else Color(0xFF2D2D2D),
                shadowElevation = 4.dp
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Dimensions.space16, vertical = Dimensions.space12),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = message,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            fontFamily = PretendardFontFamily
                        ).withLetterSpacingPercent(-2.5f),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

/**
 * Toast를 관리하는 State 클래스
 */
data class ToastState(
    val message: String = "",
    val isError: Boolean = true
)

/**
 * Toast를 표시하는 Composable (Box 내부에서 사용)
 */
@Composable
fun BoxScope.ToastMessage(
    toastState: ToastState,
    onDismiss: () -> Unit,
    duration: Long = 3000L,
    modifier: Modifier = Modifier
) {
    if (toastState.message.isNotEmpty()) {
        Toast(
            message = toastState.message,
            onDismiss = onDismiss,
            duration = duration,
            isError = toastState.isError,
            modifier = modifier.align(Alignment.BottomCenter)
        )
    }
}