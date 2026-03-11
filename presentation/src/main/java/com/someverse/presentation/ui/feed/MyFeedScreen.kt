package com.someverse.presentation.ui.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.someverse.domain.model.Feed
import com.someverse.presentation.R
import com.someverse.presentation.components.SomeVerseTopBar
import com.someverse.presentation.ui.theme.Dimensions
import com.someverse.presentation.ui.theme.SomeverseTheme

@Composable
fun MyFeedScreen(
    viewModel: MyFeedViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    MyFeedScreenContent(
        feeds = uiState.feeds
    )
}

@Composable
fun MyFeedScreenContent(
    modifier: Modifier = Modifier,
    feeds: List<Feed> = emptyList()
) {
    val iconColor = Color(0xFF9098A6)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = Dimensions.topPadding)
            .background(Color(0xFFFAFAFA))
    ) {
        SomeVerseTopBar(
            textTitle = "내 피드",
            leadingIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_dismiss),
                        contentDescription = "뒤로가기",
                        tint = iconColor,
                        modifier = Modifier.size(28.dp)
                    )
                }
            },
            trailingIcons = listOf {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add_image),
                        contentDescription = "추가",
                        tint = iconColor,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            contentPadding = PaddingValues(vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(feeds) { feed ->
                MyFeedCard(feed = feed)
            }
        }
    }
}

@Composable
fun MyFeedCard(feed: Feed) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(112.dp)
            .clip(RoundedCornerShape(36.dp))
            .background(Color(0xFFFFFFFF))
            .padding(vertical = 22.dp)
            .padding(start = 20.dp, end = 28.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.Top
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 포스터
                Box(
                    modifier = Modifier
                        .size(width = 48.dp, height = 69.dp)
                        .background(Color.LightGray)
                )

                Spacer(modifier = Modifier.width(Dimensions.space12))

                // 텍스트 영역
                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = feed.movieTitle,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.width(Dimensions.space4))

                        Text(
                            text = "(${feed.movieReleaseDate.take(4)})",
                            fontSize = 13.sp,
                            color = Color.Gray
                        )
                    }

                    Spacer(modifier = Modifier.height(Dimensions.space8))

                    Text(
                        text = feed.content,
                        fontSize = 14.sp,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.width(9.dp))

            // 더보기 버튼
            IconButton(
                onClick = { },
                modifier = Modifier.size(24.dp) // 버튼 터치 영역 최적화
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_more_vert),
                    contentDescription = "메뉴",
                    tint = Color(0xFF9098A6),
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, name = "내 피드 화면 미리보기")
fun MyFeedScreenPreview() {
    SomeverseTheme {
        MyFeedScreenContent(
            feeds = listOf(
                Feed(
                    id = 1,
                    content = "오늘 본 인터스텔라 정말 최고였어요!",
                    nickName = "보름달투",
                    profileImage = "https://example.com/image1.jpg",
                    movieId = 157336,
                    movieTitle = "인터스텔라",
                    movieOverview = "우주를 배경으로 한 SF 대작",
                    moviePosterPath = "path/to/poster.jpg",
                    movieReleaseDate = "2014-11-07",
                    createdAt = "2024-12-22T10:00:00"
                ),
                Feed(
                    id = 1,
                    content = "오늘 본 인터스텔라 정말 최고였어요!",
                    nickName = "보름달투",
                    profileImage = "https://example.com/image1.jpg",
                    movieId = 157336,
                    movieTitle = "인터스텔라",
                    movieOverview = "우주를 배경으로 한 SF 대작",
                    moviePosterPath = "path/to/poster.jpg",
                    movieReleaseDate = "2014-11-07",
                    createdAt = "2024-12-22T10:00:00"
                ),
                Feed(
                    id = 1,
                    content = "오늘 본 인터스텔라 정말 최고였어요!",
                    nickName = "보름달투",
                    profileImage = "https://example.com/image1.jpg",
                    movieId = 157336,
                    movieTitle = "인터스텔라",
                    movieOverview = "우주를 배경으로 한 SF 대작",
                    moviePosterPath = "path/to/poster.jpg",
                    movieReleaseDate = "2014-11-07",
                    createdAt = "2024-12-22T10:00:00"
                ),
            )
        )
    }
}

@Composable
@Preview(showBackground = true, name = "피드 아이템 개별 미리보기")
fun MyFeedCardPreview() {
    MyFeedCard(
        feed = Feed(
            id = 1,
            content = "오늘 본 인터스텔라 정말 최고였어요!",
            nickName = "보름달투",
            profileImage = "https://example.com/image1.jpg",
            movieId = 157336,
            movieTitle = "인터스텔라",
            movieOverview = "우주를 배경으로 한 SF 대작",
            moviePosterPath = "path/to/poster.jpg",
            movieReleaseDate = "2014-11-07",
            createdAt = "2024-12-22T10:00:00"
        )
    )
}