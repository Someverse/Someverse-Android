package com.someverse.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.someverse.presentation.R
import com.someverse.presentation.ui.theme.PretendardFontFamily
import com.someverse.presentation.ui.theme.PrimaryPurple
import com.someverse.presentation.ui.theme.withLetterSpacingPercent

/**
 * 위치 선택 항목 (도시 또는 구/군 선택용)
 */
@Composable
fun DropdownItem(
    text: String,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge.copy(
                fontFamily = PretendardFontFamily,
                lineHeight = 22.sp,
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
            ).withLetterSpacingPercent(-2.5f),
            color = if (isSelected) PrimaryPurple else Color(0xFF9098A6)
        )
    }
}

/**
 * 도시 목록 컴포넌트
 */
@Composable
fun CityList(
    cities: List<String>,
    selectedCity: String?,
    onCitySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(vertical = 6.dp)
    ) {
        items(cities) { city ->
            DropdownItem(
                text = city,
                isSelected = city == selectedCity,
                onClick = { onCitySelected(city) }
            )
        }
    }
}

/**
 * 구/군 목록 컴포넌트
 */
@Composable
fun DistrictList(
    districts: List<String>,
    onDistrictSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(vertical = 6.dp)
    ) {
        items(districts) { district ->
            DropdownItem(
                text = district,
                onClick = { onDistrictSelected(district) }
            )
        }
    }
}

/**
 * 위치 칩 (선택된 위치 표시용)
 */
@Composable
fun LocationChip(
    city: String,
    district: String,
    onRemove: () -> Unit
) {
    Box(modifier = Modifier.padding(vertical = 4.dp)) {
        Surface(
            shape = RoundedCornerShape(50.dp),
            color = Color(0xFFEBEFF5)
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$city $district",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 26.sp
                    ).withLetterSpacingPercent(-2.5f),
                    color = Color(0xFF9098A6)
                )
            }
        }

        // X 버튼 (위치 수정)
        Box(
            modifier = Modifier
                .size(15.dp)
                .align(Alignment.TopEnd)
                .offset(x = (-12).dp, y = (-6).dp)
                .clip(CircleShape)
                .background(Color(0xFF9098A6))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onRemove
                ),
            contentAlignment = Alignment.Center
        ) {
            // X 이미지
            Icon(
                painter = painterResource(id = R.drawable.ic_cancel_circle),
                contentDescription = "Remove",
                modifier = Modifier.size(15.dp),
                tint = Color(0xFF9098A6)
            )
        }
    }
}

