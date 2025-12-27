package com.someverse.presentation.ui.matching

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.someverse.presentation.R
import com.someverse.presentation.ui.theme.*

/**
 * Matching Screen
 * - User matching view with profile cards
 */
@Composable
fun MatchingScreen() {
    // Sample data - 나중에 ViewModel에서 가져오기
    val profiles =
        listOf(
            MatchingProfile(
                name = "마포구보안관2",
                age = 28,
                locations = listOf("서울특별시 성북구" to true, "경기도 화성시" to false),
                genres = listOf("뮤지컬" to true, "스릴러/범죄" to true, "드라마" to false, "코미디" to false),
            ),
            MatchingProfile(
                name = "굿데이",
                age = 29,
                locations = listOf("서울특별시 관악구" to true, "경기도 화성시" to false),
                genres = listOf("뮤지컬" to true, "스릴러/범죄" to true, "드라마" to false, "코미디" to false),
            ),
        )

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(White)
                .statusBarsPadding(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            // 로고
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.Start,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_logo_with_text),
                    contentDescription = "SOMEVERSE",
                    tint = Color.Unspecified,
                    modifier =
                        Modifier
                            .width(110.dp)
                            .height(20.dp),
                )
            }

            // 매칭 카드 리스트
            LazyColumn(
                modifier = Modifier.fillMaxSize().background(ChatBackground),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 20.dp, top = 40.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                items(profiles) { profile ->
                    MatchingCard(profile = profile)
                }
            }
        }
    }
}

/**
 * 매칭 프로필 카드
 */
@Composable
fun MatchingCard(profile: MatchingProfile) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 14.dp,
                    shape = RoundedCornerShape(36.dp),
                    ambientColor = SelectedRed.copy(alpha = 0.05f),
                ),
        shape = RoundedCornerShape(36.dp),
        colors = CardDefaults.cardColors(containerColor = White),
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 15.dp, vertical = 38.dp),
            ) {
                // 상단: 이름, 나이, 알림 아이콘
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "${profile.name}, ${profile.age}세",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Black,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_report),
                        contentDescription = "신고",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(20.dp),
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // 중앙: 프로필 이미지 + 영화 포스터 박스들
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    // 프로필 이미지 (원형)
                    Box(
                        modifier =
                            Modifier
                                .size(283.dp)
                                .clip(CircleShape)
                                .background(ChipBackgroundGray),
                        contentAlignment = Alignment.Center,
                    ) {
                        // 실제 프로필 이미지가 여기에 들어갈 예정
                        Text(
                            text = profile.name.first().toString(),
                            fontSize = 80.sp,
                            fontWeight = FontWeight.Bold,
                            color = Neutral80,
                        )
                    }

                    // 영화 포스터 박스 3개 - 프로필 이미지와 겹치도록 위로 올림
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.Bottom,
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .offset(y = (-40).dp),
                    ) {
                        // 왼쪽 박스 (왼쪽으로 회전)
                        Box(
                            modifier =
                                Modifier
                                    .graphicsLayer { rotationZ = -20f }
                                    .width(81.dp)
                                    .height(116.dp)
                                    .clip(RoundedCornerShape(7.dp))
                                    .background(Black),
                        )

                        // 가운데 박스 (정면) - 위로 올려서 양쪽보다 높게 배치
                        Box(
                            modifier =
                                Modifier
                                    .offset(y = (-20).dp)
                                    .width(81.dp)
                                    .height(116.dp)
                                    .clip(RoundedCornerShape(7.dp))
                                    .background(Black),
                        )

                        // 오른쪽 박스 (오른쪽으로 회전)
                        Box(
                            modifier =
                                Modifier
                                    .graphicsLayer { rotationZ = 20f }
                                    .width(81.dp)
                                    .height(116.dp)
                                    .clip(RoundedCornerShape(7.dp))
                                    .background(Black),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // 위치 섹션
                LocationSection(locations = profile.locations)

                Spacer(modifier = Modifier.height(16.dp))

                // 취향 섹션
                GenreSection(genres = profile.genres)
            }
        }
    }
}

/**
 * 위치 섹션
 */
@Composable
fun LocationSection(locations: List<Pair<String, Boolean>>) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "위치",
                tint = Neutral80,
                modifier = Modifier.size(16.dp),
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "위치",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Neutral80,
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            locations.forEach { (location, isSelected) ->
                LocationChip(text = location, isSelected = isSelected)
            }
        }
    }
}

/**
 * 취향 섹션
 */
@Composable
fun GenreSection(genres: List<Pair<String, Boolean>>) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_movie_taste),
                contentDescription = "취향",
                tint = Color.Unspecified,
                modifier = Modifier.size(16.dp),
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "취향",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Neutral80,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 칩들을 2줄로 표시
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            genres.forEach { (genre, isSelected) ->
                GenreChip(text = genre, isSelected = isSelected)
            }
        }
    }
}

/**
 * 위치 칩
 */
@Composable
fun LocationChip(
    text: String,
    isSelected: Boolean,
) {
    val backgroundColor =
        if (isSelected) {
            Brush.horizontalGradient(
                colors = listOf(GradationStart, GradationEnd),
            )
        } else {
            Brush.horizontalGradient(
                colors = listOf(ChipBackgroundGray, ChipBackgroundGray),
            )
        }

    Box(
        modifier =
            Modifier
                .background(
                    brush = backgroundColor,
                    shape = RoundedCornerShape(50.dp),
                ).padding(horizontal = 12.dp, vertical = 8.dp),
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = if (isSelected) White else ChipGray,
            lineHeight = 26.sp,
        )
    }
}

/**
 * 취향 칩
 */
@Composable
fun GenreChip(
    text: String,
    isSelected: Boolean,
) {
    val backgroundColor =
        if (isSelected) {
            Brush.horizontalGradient(
                colors = listOf(GradationStart, GradationEnd),
            )
        } else {
            Brush.horizontalGradient(
                colors = listOf(ChipBackgroundGray, ChipBackgroundGray),
            )
        }

    Box(
        modifier =
            Modifier
                .background(
                    brush = backgroundColor,
                    shape = RoundedCornerShape(50.dp),
                ).padding(horizontal = 12.dp, vertical = 8.dp),
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = if (isSelected) White else ChipGray,
        )
    }
}

/**
 * 매칭 프로필 데이터 모델
 */
data class MatchingProfile(
    val name: String,
    val age: Int,
    val locations: List<Pair<String, Boolean>>, // (위치, 선택 여부)
    val genres: List<Pair<String, Boolean>>, // (장르, 선택 여부)
)
