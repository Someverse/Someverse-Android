package com.someverse.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.someverse.presentation.R
import com.someverse.presentation.ui.theme.Dimensions
import com.someverse.presentation.ui.theme.KakaoYellow

/**
 * Standard button with icon and text
 */
@Composable
fun TextWithImageButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconResId: Int? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    cornerRadius: Float = Dimensions.radiusLarge.value,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        colors =
            ButtonDefaults.buttonColors(
                containerColor = backgroundColor,
                contentColor = contentColor,
                disabledContainerColor = backgroundColor.copy(alpha = 0.5f),
                disabledContentColor = contentColor.copy(alpha = 0.5f),
            ),
        shape = RoundedCornerShape(cornerRadius),
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = Dimensions.space16)
                .height(Dimensions.buttonHeight),
        enabled = enabled,
        contentPadding = PaddingValues(horizontal = Dimensions.space16),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Dimensions.space12),
        ) {
            iconResId?.let {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    modifier = Modifier.size(Dimensions.iconSize),
                )
            }

            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}

/**
 * Kakao login button that matches Kakao branding requirements
 */
@Composable
fun KakaoLoginButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    TextWithImageButton(
        text = "카카오 로그인",
        backgroundColor = KakaoYellow,
        contentColor = Color.Black,
        iconResId = R.drawable.ic_kakao,
        cornerRadius = Dimensions.radiusLarge.value,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
    )
}
