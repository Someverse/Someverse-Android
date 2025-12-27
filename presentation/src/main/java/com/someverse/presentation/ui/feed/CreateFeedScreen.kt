package com.someverse.presentation.ui.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.someverse.presentation.R
import com.someverse.presentation.components.TopBarWithAction
import com.someverse.presentation.ui.theme.*

/**
 * Create Feed Screen
 * - Create new feed for a movie
 */
@Composable
fun CreateFeedScreen(
    onBackClick: () -> Unit,
    onFeedCreated: () -> Unit,
    onSearchClick: () -> Unit,
    viewModel: CreateFeedViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    // Handle success navigation
    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            onFeedCreated()
            viewModel.resetSuccess()
        }
    }

    // Show error snackbar
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(uiState.error) {
        uiState.error?.let { error ->
            snackbarHostState.showSnackbar(error)
            viewModel.clearError()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopBarWithAction(
                title = "피드 작성하기",
                onBackClick = onBackClick,
                actionText = "저장",
                onActionClick = { viewModel.onSaveClick() },
                actionEnabled = uiState.isSaveEnabled && !uiState.isLoading,
                backgroundColor = Color.White,
            )
        },
    ) { paddingValues ->
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(White)
                    .padding(paddingValues),
        ) {
            // Loading overlay
            if (uiState.isLoading) {
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.3f)),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(color = PrimaryPurple)
                }
            }

            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(horizontal = 32.dp, vertical = 20.dp),
            ) {
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(132.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    // Left side - Movie poster
                    Box(
                        modifier =
                            Modifier
                                .width(92.dp)
                                .fillMaxHeight()
                                .background(
                                    color = Color(0xFFD9D9D9),
                                    shape = RoundedCornerShape(8.dp),
                                ),
                    )

                    // Right side - Search and content input
                    Column(
                        modifier =
                            Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        // Search button
                        SearchButton(
                            onClick = onSearchClick,
                            placeholder = "검색",
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .height(44.dp),
                        )

                        // Content input field
                        ContentInputField(
                            value = uiState.content,
                            onValueChange = { viewModel.onContentChange(it) },
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Movie title - aligned with poster center
                Box(
                    modifier = Modifier.width(92.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text =
                            uiState.selectedMovieTitle.ifEmpty {
                                "영화 제목"
                            },
                        style =
                            MaterialTheme.typography.bodyMedium.copy(
                                fontFamily = PretendardFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                lineHeight = 18.sp,
                            ),
                        color =
                            if (uiState.selectedMovieTitle.isNotEmpty()) {
                                Color.Black
                            } else {
                                ChipGray
                            },
                    )
                }
            }
        }
    }
}

/**
 * Search Button
 * Clickable box that navigates to search screen
 */
@Composable
private fun SearchButton(
    onClick: () -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .background(
                    color = Neutral20,
                    shape = RoundedCornerShape(16.dp),
                ).clickable(onClick = onClick)
                .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = placeholder,
                style =
                    MaterialTheme.typography.bodyMedium
                        .copy(
                            fontFamily = PretendardFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                        ).withLetterSpacingPercent(-2.5f),
                color = Neutral80,
            )

            // Search icon
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "검색",
                modifier = Modifier.size(16.dp),
                tint = Neutral80,
            )
        }
    }
}

/**
 * Content Input Field with edit icon
 */
@Composable
private fun ContentInputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .background(
                    color = Neutral20,
                    shape = RoundedCornerShape(16.dp),
                ).padding(16.dp),
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxSize(),
            textStyle =
                MaterialTheme.typography.bodyMedium
                    .copy(
                        fontFamily = PretendardFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        color = Color.Black,
                    ).withLetterSpacingPercent(-2.5f),
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.fillMaxSize()) {
                    if (value.isEmpty()) {
                        Text(
                            text = "",
                            style =
                                MaterialTheme.typography.bodyMedium.copy(
                                    fontFamily = PretendardFontFamily,
                                    fontSize = 16.sp,
                                ),
                            color = Color(0xFF909AA6),
                        )
                    }
                    innerTextField()

                    // Edit icon at bottom right
                    Icon(
                        painter = painterResource(id = R.drawable.ic_pencil),
                        contentDescription = null,
                        modifier =
                            Modifier
                                .align(Alignment.BottomEnd)
                                .size(20.dp),
                        tint = Neutral80,
                    )
                }
            },
        )
    }
}
