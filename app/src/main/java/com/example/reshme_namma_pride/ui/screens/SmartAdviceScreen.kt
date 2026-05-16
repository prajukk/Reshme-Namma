package com.example.reshme_namma_pride.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TipsAndUpdates
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reshme_namma_pride.ui.components.PremiumButton
import com.example.reshme_namma_pride.ui.theme.*

@Composable
fun SmartAdviceScreen(
    status: String = "Caution",
    advice: String = "Open windows and increase ventilation to lower the temperature. Ensure adequate spacing between rearing trays.",
    onBack: () -> Unit
) {
    val (backgroundColor, gradient, icon) = when (status.lowercase()) {
        "safe" -> Triple(GreenPrimary, listOf(GreenGradientStart, GreenGradientEnd), Icons.Default.CheckCircle)
        "danger" -> Triple(RedAccent, listOf(RedGradientStart, RedGradientEnd), Icons.Default.Warning)
        else -> Triple(OrangeAccent, listOf(OrangeGradientStart, OrangeGradientEnd), Icons.Default.TipsAndUpdates)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(gradient))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier = Modifier.size(140.dp),
            shape = CircleShape,
            color = Color.White.copy(alpha = 0.2f)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(72.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = status.uppercase(),
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.Black,
                color = Color.White,
                letterSpacing = 4.sp
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = advice,
            style = MaterialTheme.typography.headlineSmall.copy(
                color = Color.White,
                lineHeight = 36.sp,
                fontWeight = FontWeight.SemiBold
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(80.dp))

        PremiumButton(
            text = "Got it",
            onClick = onBack,
            gradient = listOf(Color.White, Color.White),
            contentColor = backgroundColor
        )
    }
}
