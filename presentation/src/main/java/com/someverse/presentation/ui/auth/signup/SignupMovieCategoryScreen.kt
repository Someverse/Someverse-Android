package com.someverse.presentation.ui.auth.signup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.someverse.presentation.components.GradientButton
import com.someverse.presentation.components.SimpleTopBar
import com.someverse.presentation.components.Toast
import com.someverse.presentation.ui.theme.*

/**
 * 영화 카테고리 선택 화면
 */
@Composable
fun SignupMovieCategoryScreen(
    onNext: () -> Unit,
    onBack:(() -> Unit)? = null,
    viewModel: SignupMovieCategoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    // 카테고리 선택 완료 시 다음 화면으로 이동
    LaunchedEffect(uiState.canProceed) {
        if (uiState.canProceed) {
            onNext()
            viewModel.resetProceedState()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TopBar 추가
            SimpleTopBar(
                title = "취향 입력",
                onBackClick = onBack
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimensions.screenPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 상단 여백
                Spacer(modifier = Modifier.height(Dimensions.space24))

                // 제목
                Text(
                    text = "어떤 장르의 영화를\n좋아하세요?",
                    style = MaterialTheme.typography.displaySmall,
                    textAlign = TextAlign.Start,
                    color = Black,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(Dimensions.space12))

                // 설명
                Text(
                    text = "최대 5개 까지 고를 수 있어요!",
                    style = MaterialTheme.typography.bodyLarge,
                    color = DescGray,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.weight(1f))

                // 카테고리 칩과 버튼을 묶는 Column
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(86.dp)
                ) {
                    // 카테고리 칩들 - FlowRow로 가로 배치
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(Dimensions.space8),
                        verticalArrangement = Arrangement.spacedBy(Dimensions.space12),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        uiState.movieCategories.forEach { category ->
                            CategoryChip(
                                text = category,
                                selected = uiState.selectedCategories.contains(category),
                                onSelectionChanged = {
                                    viewModel.toggleCategory(category)
                                }
                            )
                        }
                    }

                    // 다음 버튼 - components 폴더의 GradientButton 사용
                    // UIState에서 선택 여부 체크
                    val hasSelection = uiState.selectedCategories.isNotEmpty()

                    if (hasSelection) {
                        // 선택된 상태: 보라-핑크 그라데이션
                        GradientButton(
                            text = "인생영화 고르기",
                            onClick = { viewModel.submitCategories() },
                            enabled = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                        )
                    } else {
                        // 선택 안 된 상태: 연한 보라-연한 노랑 그라데이션
                        GradientButton(
                            text = "인생영화 고르기",
                            onClick = { viewModel.submitCategories() },
                            enabled = false,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(Dimensions.space48))
            }
        }

        // Toast 메시지 (하단에 표시)
        if (uiState.errorMessage.isNotEmpty()) {
            Toast(
                message = uiState.errorMessage,
                onDismiss = { /* 에러 메시지 자동 사라짐 */ },
                duration = 3000L,
                isError = true,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 100.dp)
            )
        }
    }
}

/**
 * 카테고리 선택 칩
 */
@Composable
fun CategoryChip(
    text: String,
    selected: Boolean,
    onSelectionChanged: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (selected) SelectedRed else ChipBackgroundGray
    val contentColor = if (selected) White else ChipGray

    Surface(
        modifier = modifier
            .heightIn(min = 35.dp)
            .toggleable(
                value = selected,
                onValueChange = { onSelectionChanged() }
            ),
        shape = RoundedCornerShape(50),
        color = backgroundColor,
        tonalElevation = 0.dp
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = contentColor,
                textAlign = TextAlign.Center
            )
        }
    }
}