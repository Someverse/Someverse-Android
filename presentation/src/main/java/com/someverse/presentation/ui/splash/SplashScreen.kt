package com.someverse.presentation.ui.splash

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.someverse.presentation.R
import com.someverse.presentation.ui.theme.GradationEnd
import com.someverse.presentation.ui.theme.GradationStart
import kotlinx.coroutines.delay

/**
 * Splash Screen
 * - 앱 시작 시 보여지는 스플래시 화면
 * - 로고와 함께 브랜드 아이덴티티를 표현
 */
@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {
    val view = LocalView.current
    val window = (view.context as? Activity)?.window

    // 시스템 바 아이콘 색상 설정
    DisposableEffect(window) {
        window?.let {
            // 시스템 바 아이콘을 검은색으로 설정
            WindowCompat.getInsetsController(it, view)?.apply {
                isAppearanceLightStatusBars = true  // 밝은 배경용 (검은 아이콘)
                isAppearanceLightNavigationBars = true
            }
        }

        onDispose {
            // Cleanup if needed
        }
    }

    LaunchedEffect(key1 = true) {
        delay(2000) // 2초 후 다음 화면으로 이동
        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        GradationStart,
                        GradationEnd
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 로고 이미지
            Image(
                painter = painterResource(id = R.drawable.ic_splash_logo),
                contentDescription = "Someverse Logo",
                modifier = Modifier.size(70.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 앱 이름
            Text(
                text = "Someverse",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
