package com.someverse.presentation.ui.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Feed Screen
 * - Main feed view
 */
@Composable
fun FeedScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "피드",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "피드 화면입니다",
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
