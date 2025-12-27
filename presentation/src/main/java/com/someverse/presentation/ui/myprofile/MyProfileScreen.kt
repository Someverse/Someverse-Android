package com.someverse.presentation.ui.myprofile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.someverse.presentation.R
import com.someverse.presentation.components.GradientButton
import com.someverse.presentation.ui.theme.*

/**
 * My Profile Screen
 * - User's profile view with feeds, preferences, and themes
 */
@Composable
fun MyProfileScreen() {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(ChatBackground)
                .statusBarsPadding()
                .verticalScroll(rememberScrollState()),
    ) {
        // Top bar
        TopBar()

        Spacer(modifier = Modifier.height(46.dp))

        // Profile section
        ProfileSection()

        Spacer(modifier = Modifier.height(34.dp))

        // Feed section
        FeedSection()

        Spacer(modifier = Modifier.height(20.dp))

        // Divider
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(12.dp)
                    .background(Neutral20),
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Preference curation section
        PreferenceCurationSection()

        Spacer(modifier = Modifier.height(20.dp))

        // Divider
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(12.dp)
                    .background(Neutral20),
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Preference theme section
        PreferenceThemeSection()

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun TopBar() {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // SOMEVERSE Logo placeholder
        Text(
            text = "SOMEVERSE",
            fontSize = 14.sp,
            fontWeight = FontWeight.Black,
            color = PrimaryPurple,
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "포인트",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = DescGray,
            )

            Box(
                modifier =
                    Modifier
                        .width(1.dp)
                        .height(20.dp)
                        .background(Neutral80),
            )

            Text(
                text = "설정",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = DescGray,
            )
        }
    }
}

@Composable
private fun ProfileSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Profile image with edit badge
        Box(
            contentAlignment = Alignment.BottomEnd,
        ) {
            // Profile image
            Box(
                modifier =
                    Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(Neutral20),
                contentAlignment = Alignment.Center,
            ) {
                // Placeholder for profile image
                // In production, use AsyncImage or similar
            }

            // Edit badge with gradient
            Box(
                modifier =
                    Modifier
                        .offset(x = (-4).dp, y = (-4).dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(GradationStart, GradationEnd),
                            ),
                        ).padding(horizontal = 8.dp, vertical = 3.dp),
            ) {
                Text(
                    text = "편집",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Name and age
        Text(
            text = "드라마메이커, 24세",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = DescGray,
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Location
        Text(
            text = "성북구, 관악구",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = DescGray,
        )
    }
}

@Composable
private fun FeedSection() {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "내 피드",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Black,
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Empty state message
        Text(
            text = "아직 작성한 피드가 없어요!\n피드를 작성해 볼까요?",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Neutral80,
            lineHeight = 14.4.sp,
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Create feed button
        GradientButton(
            text = "피드 작성하기",
            onClick = { /* TODO: Navigate to feed creation */ },
            enabled = true,
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
    }
}

@Composable
private fun PreferenceCurationSection() {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
    ) {
        // Header
        Text(
            text = "취향 큐레이션",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Black,
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Favorite Genre section
        FavoriteGenreSection()

        Spacer(modifier = Modifier.height(12.dp))

        // Favorite Works section
        FavoriteWorksSection()
    }
}

@Composable
private fun FavoriteGenreSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        // Title with icon
        Row(
            modifier =
                Modifier
                    .clip(RoundedCornerShape(7.dp))
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(Color(0xFFEBF0FB), Color(0xFFF6FFFB)),
                        ),
                    ).padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            // Icon placeholder
            Box(
                modifier =
                    Modifier
                        .size(14.dp)
                        .background(Color(0xFF5088E2), CircleShape),
            )

            Text(
                text = "좋아하는 장르",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF5088E2),
            )

            Spacer(modifier = Modifier.weight(1f))

            // Edit button
            Box(
                modifier =
                    Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(Neutral20)
                        .padding(horizontal = 8.dp, vertical = 3.dp),
            ) {
                Text(
                    text = "편집",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Neutral80,
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Genre chips
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            GenreChip("뮤지컬")
            GenreChip("공포/호러")
            GenreChip("SF")
            GenreChip("애니메이션")
        }
    }
}

@Composable
private fun GenreChip(text: String) {
    Box(
        modifier =
            Modifier
                .clip(RoundedCornerShape(50.dp))
                .background(ChipBackgroundGray)
                .padding(horizontal = 12.dp, vertical = 8.dp),
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = ChipGray,
        )
    }
}

@Composable
private fun FavoriteWorksSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        // Title with icon
        Row(
            modifier =
                Modifier
                    .clip(RoundedCornerShape(7.dp))
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(Color(0xFFEBF0FB), Color(0xFFF6FFFB)),
                        ),
                    ).padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            // Icon placeholder
            Box(
                modifier =
                    Modifier
                        .size(14.dp)
                        .background(Color(0xFF5088E2), CircleShape),
            )

            Text(
                text = "좋아하는 작품",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF5088E2),
            )

            Spacer(modifier = Modifier.weight(1f))

            // Edit button
            Box(
                modifier =
                    Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(Neutral20)
                        .padding(horizontal = 8.dp, vertical = 3.dp),
            ) {
                Text(
                    text = "편집",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Neutral80,
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Works grid (4 items)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            repeat(4) {
                Box(
                    modifier =
                        Modifier
                            .weight(1f)
                            .aspectRatio(0.7f)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFFD9D9D9)),
                )
            }
        }
    }
}

@Composable
private fun PreferenceThemeSection() {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
    ) {
        // Header
        Text(
            text = "취향 테마",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Black,
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Empty state message
        Text(
            text = "아직 취향 테마를 입력하지 않았어요!\n취향을 입력해 볼까요?",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Neutral80,
            lineHeight = 14.4.sp,
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Enter preference theme button
        GradientButton(
            text = "취향테마 입력하기",
            onClick = { /* TODO: Navigate to theme input */ },
            enabled = true,
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
    }
}
