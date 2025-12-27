package com.someverse.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.someverse.presentation.R

// Pretendard 폰트 정의
val PretendardFontFamily =
    FontFamily(
        Font(R.font.pretendard_regular, FontWeight.Normal),
        Font(R.font.pretendard_medium, FontWeight.Medium),
        Font(R.font.pretendard_semibold, FontWeight.SemiBold),
        Font(R.font.pretendard_bold, FontWeight.Bold),
    )

/**
 * TextStyle에 퍼센트 기반의 lineHeight를 적용하는 확장 함수
 * @param percent lineHeight의 퍼센트 값 (예: 120f는 fontSize의 120%를 의미)
 */
fun TextStyle.withLineHeightPercent(percent: Float): TextStyle = this.copy(lineHeight = fontSize * (percent / 100f))

/**
 * TextStyle에 퍼센트 기반의 letterSpacing을 적용하는 확장 함수
 * @param percent letterSpacing의 퍼센트 값 (예: -2.5f는 fontSize의 -2.5%를 의미)
 */
fun TextStyle.withLetterSpacingPercent(percent: Float): TextStyle = this.copy(letterSpacing = fontSize * (percent / 100f))

// Define the typography scale for Someverse app
val SomeverseTypography =
    Typography(
        // Headings
        displayLarge =
            TextStyle(
                fontFamily = PretendardFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
            ).withLineHeightPercent(150f).withLetterSpacingPercent(-2.5f),
        displayMedium =
            TextStyle(
                fontFamily = PretendardFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                lineHeight = 40.sp,
                letterSpacing = 0.sp,
            ),
        displaySmall =
            TextStyle(
                fontFamily = PretendardFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                lineHeight = 36.sp,
                letterSpacing = 0.sp,
            ),
        // Titles
        titleLarge =
            TextStyle(
                fontFamily = PretendardFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
            ).withLineHeightPercent(133f).withLetterSpacingPercent(4.2f),
        titleMedium =
            TextStyle(
                fontFamily = PretendardFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
            ).withLineHeightPercent(140f).withLetterSpacingPercent(0.75f),
        titleSmall =
            TextStyle(
                fontFamily = PretendardFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
            ).withLineHeightPercent(120f).withLetterSpacingPercent(-2.5f),
        // Body text
        bodyLarge =
            TextStyle(
                fontFamily = PretendardFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
            ).withLineHeightPercent(120f).withLetterSpacingPercent(-2.5f),
        bodyMedium =
            TextStyle(
                fontFamily = PretendardFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
            ).withLineHeightPercent(143f).withLetterSpacingPercent(1.79f),
        bodySmall =
            TextStyle(
                fontFamily = PretendardFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
            ).withLineHeightPercent(133f).withLetterSpacingPercent(3.33f),
        // Labels & Buttons
        labelLarge =
            TextStyle(
                fontFamily = PretendardFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
            ).withLineHeightPercent(150f).withLetterSpacingPercent(0.63f),
        labelMedium =
            TextStyle(
                fontFamily = PretendardFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
            ).withLineHeightPercent(143f).withLetterSpacingPercent(3.57f),
        labelSmall =
            TextStyle(
                fontFamily = PretendardFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
            ).withLineHeightPercent(133f).withLetterSpacingPercent(4.17f),
    )

// Alias for backward compatibility
val Typography = SomeverseTypography
