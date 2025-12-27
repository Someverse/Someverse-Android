package com.someverse.presentation.ui.auth.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.someverse.presentation.R
import com.someverse.presentation.components.GradientButton
import com.someverse.presentation.components.TopBarWithAction
import com.someverse.presentation.ui.theme.DescGray
import com.someverse.presentation.ui.theme.PretendardFontFamily
import com.someverse.presentation.ui.theme.PrimaryPurple

/**
 * 영화 선택 화면 (Figma 디자인 기반)
 */
@Composable
fun SignupMovieTasteScreen(
    onNext: () -> Unit,
    viewModel: SignupMovieTasteViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    var isSearchMode by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    // 선택 완료 시 다음 화면으로 이동
    LaunchedEffect(uiState.canProceed) {
        if (uiState.canProceed) {
            onNext()
            viewModel.resetProceedState()
        }
    }

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(Color.White),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
        ) {
            // TopBar - 검색 모드에 따라 다른 UI 표시
            if (isSearchMode) {
                SearchTopBar(
                    searchQuery = searchQuery,
                    onSearchQueryChange = { searchQuery = it },
                    onBackClick = {
                        isSearchMode = false
                        searchQuery = ""
                    },
                )
            } else {
                TopBarWithAction(
                    title = "취향 입력",
                    onBackClick = { /* TODO: 뒤로가기 구현 */ },
                    actionIconRes = R.drawable.ic_search,
                    onActionClick = { isSearchMode = true },
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 안내 문구
            Text(
                text = "최소 5개 이상의 영화를 골라주세요!",
                style =
                    MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF616772),
                    ),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 0.dp),
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 영화 포스터 그리드
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier =
                    Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(bottom = 200.dp),
            ) {
                items(uiState.movies) { movie ->
                    MoviePosterItem(
                        movie = movie,
                        isSelected = uiState.selectedMovies.contains(movie.id),
                        onToggle = { viewModel.toggleMovie(movie.id) },
                    )
                }
            }
        }

        // 하단 그라데이션 영역과 버튼
        BottomSection(
            selectedCount = uiState.selectedMovies.size,
            isEnabled = uiState.isNextEnabled,
            onComplete = { viewModel.submitMovies() },
        )
    }
}

/**
 * 검색 TopBar
 */
@Composable
fun SearchTopBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onBackClick: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.White)
                .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            // 뒤로가기 버튼
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(24.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_dismiss),
                    contentDescription = "뒤로가기",
                    tint = Color(0xFF9098A6),
                )
            }

            // 검색 입력 필드
            Box(
                modifier =
                    Modifier
                        .weight(1f)
                        .height(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFF5F6F7)),
                contentAlignment = Alignment.CenterStart,
            ) {
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    BasicTextField(
                        value = searchQuery,
                        onValueChange = onSearchQueryChange,
                        modifier =
                            Modifier
                                .weight(1f)
                                .focusRequester(focusRequester),
                        textStyle =
                            TextStyle(
                                fontSize = 14.sp,
                                fontFamily = PretendardFontFamily,
                                color = Color(0xFF616772),
                            ),
                        singleLine = true,
                        cursorBrush = SolidColor(PrimaryPurple),
                        decorationBox = { innerTextField ->
                            if (searchQuery.isEmpty()) {
                                Text(
                                    text = "배우, 카테고리, 제목을 직접 검색하세요",
                                    style =
                                        TextStyle(
                                            fontSize = 14.sp,
                                            fontFamily = PretendardFontFamily,
                                            color = DescGray,
                                        ),
                                )
                            }
                            innerTextField()
                        },
                    )

                    if (searchQuery.isNotEmpty()) {
                        IconButton(
                            onClick = { onSearchQueryChange("") },
                            modifier = Modifier.size(16.dp),
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_cancel_circle),
                                contentDescription = "검색어 지우기",
                                tint = Color(0xFF9098A6),
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * 영화 포스터 아이템
 */
@Composable
fun MoviePosterItem(
    movie: Movie,
    isSelected: Boolean,
    onToggle: () -> Unit,
) {
    Card(
        modifier =
            Modifier
                .width(92.dp)
                .height(151.dp),
        shape = RoundedCornerShape(8.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = Color(0xFFD9D9D9),
            ),
        onClick = onToggle,
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .then(
                        if (isSelected) {
                            Modifier.border(
                                width = 3.dp,
                                color = PrimaryPurple,
                                shape = RoundedCornerShape(8.dp),
                            )
                        } else {
                            Modifier
                        },
                    ),
            contentAlignment = Alignment.BottomCenter,
        ) {
            // 영화 포스터 이미지가 들어갈 자리
            // TODO: 실제 이미지 로딩 구현

            // 영화 제목
            Text(
                text = movie.title,
                style =
                    MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF9098A6),
                    ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp),
            )
        }
    }
}

/**
 * 하단 섹션 (선택 개수 표시 + 완료 버튼)
 */
@Composable
fun BoxScope.BottomSection(
    selectedCount: Int,
    isEnabled: Boolean,
    onComplete: () -> Unit,
) {
    Box(
        modifier =
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(154.dp)
                .background(
                    brush =
                        Brush.verticalGradient(
                            colors =
                                listOf(
                                    Color.White.copy(alpha = 0.8f),
                                    Color.White,
                                ),
                        ),
                ),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom,
        ) {
            // 선택 개수 표시
            Text(
                text = "총 ${selectedCount}개 선택",
                style =
                    MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF616772),
                    ),
                modifier = Modifier.padding(start = 8.dp),
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 완료 버튼
            GradientButton(
                text = "인생영화 선택완료",
                onClick = onComplete,
                enabled = isEnabled,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                textStyle =
                    MaterialTheme.typography.titleMedium.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                    ),
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
