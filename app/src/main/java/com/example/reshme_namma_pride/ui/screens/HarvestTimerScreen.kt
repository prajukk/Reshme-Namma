package com.example.reshme_namma_pride.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reshme_namma_pride.ui.components.PremiumButton
import com.example.reshme_namma_pride.ui.components.PremiumCard
import com.example.reshme_namma_pride.ui.theme.*

@Composable
fun HarvestTimerScreen(onBack: () -> Unit) {
    val progressAnimation by rememberInfiniteTransition(label = "progress").animateFloat(
        initialValue = 0f,
        targetValue = 0.85f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "progress"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGrey)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        
        Text(
            text = "Harvest Countdown ⏳",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.ExtraBold,
                color = GreenPrimary
            )
        )
        Text(
            text = "Tracking your silk production",
            style = MaterialTheme.typography.bodyMedium.copy(color = TextGrey)
        )
        
        Spacer(modifier = Modifier.height(64.dp))

        // Circular Progress Timer
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                progress = { 0.85f },
                modifier = Modifier.size(240.dp),
                color = GreenPrimary,
                strokeWidth = 16.dp,
                trackColor = GreenPrimary.copy(alpha = 0.1f),
                strokeCap = StrokeCap.Round,
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "3",
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontWeight = FontWeight.Black,
                        fontSize = 80.sp,
                        color = Black
                    )
                )
                Text(
                    text = "Days Remaining",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = TextGrey
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Alert Card
        PremiumCard(containerColor = GreenPrimary.copy(alpha = 0.05f)) {
            Column(modifier = Modifier.padding(24.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        modifier = Modifier.size(40.dp),
                        shape = CircleShape,
                        color = GreenPrimary.copy(alpha = 0.1f)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                Icons.Default.NotificationsActive,
                                contentDescription = null,
                                tint = GreenPrimary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "ACTION REQUIRED",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = GreenPrimary,
                            letterSpacing = 1.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Prepare for cocoon transfer to mounting frames. Ensure all frames are disinfected.",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
                )
                Spacer(modifier = Modifier.height(16.dp))
                LinearProgressIndicator(
                    progress = { progressAnimation },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(CircleShape),
                    color = GreenPrimary,
                    trackColor = GreenPrimary.copy(alpha = 0.1f),
                    strokeCap = StrokeCap.Round
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        PremiumButton(
            text = "Back to Dashboard",
            onClick = onBack
        )
        Spacer(modifier = Modifier.height(48.dp))
    }
}
