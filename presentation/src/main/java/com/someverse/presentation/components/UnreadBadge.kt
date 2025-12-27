package com.someverse.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.someverse.presentation.ui.theme.PrimaryPurple
import com.someverse.presentation.ui.theme.White

@Composable
fun UnreadBadge(count: Int) {
    val displayText = if (count > 99) "99+" else count.toString()

    Box(
        modifier =
            Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(PrimaryPurple),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = displayText,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = White,
            letterSpacing = (-0.3).sp,
            lineHeight = (12 * 1.2).sp,
        )
    }
}
