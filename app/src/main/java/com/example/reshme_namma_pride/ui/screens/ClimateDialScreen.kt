package com.example.reshme_namma_pride.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reshme_namma_pride.ui.components.PremiumButton
import com.example.reshme_namma_pride.ui.theme.*
import kotlin.math.cos
import kotlin.math.sin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClimateDialScreen(
    currentValue: Float,
    minValue: Float = 15f,
    maxValue: Float = 40f,
    onBack: () -> Unit
) {
    val coercedValue = currentValue.coerceIn(minValue, maxValue)
    
    val animatedValue by animateFloatAsState(
        targetValue = coercedValue,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
        label = "dialValue"
    )

    val isDark = isSystemInDarkTheme()
    val secondaryTextColor = if (isDark) TextGreyDark else TextGrey
    
    // Pre-calculate theme colors to avoid composable calls inside Canvas
    val colorScheme = MaterialTheme.colorScheme
    val primaryColor = colorScheme.primary
    val surfaceColor = colorScheme.surface
    val onSurfaceColor = colorScheme.onSurface
    val backgroundColor = colorScheme.background
    val onBackgroundColor = colorScheme.onBackground

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Precision Monitor", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = backgroundColor
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Real-time environment precision",
                style = MaterialTheme.typography.bodyMedium.copy(color = secondaryTextColor)
            )
            
            Spacer(modifier = Modifier.height(48.dp))

            Box(
                modifier = Modifier.size(320.dp),
                contentAlignment = Alignment.Center
            ) {
                val trackColor = if (isDark) Color.White.copy(alpha = 0.1f) else Color.Black.copy(alpha = 0.05f)

                Canvas(modifier = Modifier.fillMaxSize()) {
                    val strokeWidth = 24.dp.toPx()
                    val radius = (size.minDimension - strokeWidth) / 2

                    // Background track
                    drawArc(
                        color = trackColor,
                        startAngle = 135f,
                        sweepAngle = 270f,
                        useCenter = false,
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                    )

                    // Gradient segments
                    drawArc(
                        brush = Brush.sweepGradient(
                            colors = listOf(primaryColor, OrangeAccent, RedAccent, RedAccent),
                            center = size.center
                        ),
                        startAngle = 135f,
                        sweepAngle = 270f,
                        useCenter = false,
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                    )

                    // Needle Logic
                    val angle = 135f + (270f * (animatedValue - minValue) / (maxValue - minValue))
                    val angleRad = Math.toRadians(angle.toDouble())
                    val needleLength = radius * 0.85f
                    val needleEndPoint = Offset(
                        x = size.center.x + needleLength * cos(angleRad).toFloat(),
                        y = size.center.y + needleLength * sin(angleRad).toFloat()
                    )

                    // Needle Shadow
                    drawLine(
                        color = Color.Black.copy(alpha = 0.15f),
                        start = size.center,
                        end = needleEndPoint + Offset(4f, 4f),
                        strokeWidth = 6.dp.toPx(),
                        cap = StrokeCap.Round
                    )

                    // Needle
                    drawLine(
                        color = onSurfaceColor,
                        start = size.center,
                        end = needleEndPoint,
                        strokeWidth = 6.dp.toPx(),
                        cap = StrokeCap.Round
                    )

                    // Center Hub
                    drawCircle(
                        color = surfaceColor,
                        radius = 16.dp.toPx()
                    )
                    drawCircle(
                        color = onSurfaceColor,
                        radius = 8.dp.toPx()
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "${animatedValue.toInt()}°C",
                        style = MaterialTheme.typography.displayMedium.copy(
                            fontWeight = FontWeight.Black,
                            color = onBackgroundColor
                        )
                    )
                    val status = when {
                        animatedValue > 30 -> "DANGER"
                        animatedValue > 27 -> "CAUTION"
                        else -> "SAFE"
                    }
                    val statusColor = when (status) {
                        "DANGER" -> RedAccent
                        "CAUTION" -> OrangeAccent
                        else -> primaryColor
                    }

                    Surface(
                        color = statusColor.copy(alpha = 0.15f),
                        shape = CircleShape,
                        border = androidx.compose.foundation.BorderStroke(1.dp, statusColor.copy(alpha = 0.5f))
                    ) {
                        Text(
                            text = status,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.labelLarge.copy(
                                color = statusColor,
                                fontWeight = FontWeight.ExtraBold,
                                letterSpacing = 2.sp
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            PremiumButton(
                text = "Update Readings",
                onClick = { /* Could navigate to entry screen or show dialog */ }
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            OutlinedButton(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Back to Dashboard", fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
