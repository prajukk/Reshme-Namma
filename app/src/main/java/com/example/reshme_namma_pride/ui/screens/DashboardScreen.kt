package com.example.reshme_namma_pride.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reshme_namma_pride.R
import com.example.reshme_namma_pride.data.local.entity.Batch
import com.example.reshme_namma_pride.ui.components.PremiumButton
import com.example.reshme_namma_pride.ui.components.PremiumCard
import com.example.reshme_namma_pride.ui.components.PremiumOutlinedButton
import com.example.reshme_namma_pride.ui.theme.*
import java.util.concurrent.TimeUnit

@Composable
fun DashboardScreen(
    temp: Float = 26f,
    hum: Float = 72f,
    status: String = "Safe",
    advice: String = "Optimal rearing conditions maintained.",
    activeBatch: Batch? = null,
    onStartNewBatch: () -> Unit = {},
    onViewClimateDial: () -> Unit = {},
    onEnterClimate: () -> Unit = {},
    onViewHarvest: () -> Unit = {},
    onViewHistory: () -> Unit = {},
    onViewAlerts: () -> Unit = {},
    onViewSettings: () -> Unit = {},
    onViewInsights: () -> Unit = {},
    onViewWeather: () -> Unit = {},
    onViewVoice: () -> Unit = {},
    onViewCamera: () -> Unit = {},
    onViewGrowth: () -> Unit = {},
    onViewClimateHistory: () -> Unit = {}
) {
    val statusColor = when(status.lowercase()) {
        "danger" -> RedAccent
        "caution" -> OrangeAccent
        else -> MaterialTheme.colorScheme.primary
    }

    val isDark = isSystemInDarkTheme()
    val secondaryTextColor = if (isDark) TextGreyDark else TextGrey

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {
        // Header with Logo
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, bottom = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Dashboard Logo Image
                Image(
                    painter = painterResource(id = R.drawable.reshme_logo),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(4.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "Reshme",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary,
                            letterSpacing = (-0.5).sp
                        )
                    )
                    Text(
                        text = "Namma Pride",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = secondaryTextColor,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                    )
                }
            }
            Row {
                IconButton(onClick = onViewAlerts) {
                    Icon(Icons.Default.Notifications, contentDescription = "Alerts", tint = MaterialTheme.colorScheme.primary)
                }
                IconButton(onClick = onViewSettings) {
                    Icon(Icons.Default.Settings, contentDescription = "Settings", tint = MaterialTheme.colorScheme.primary)
                }
            }
        }

        // Active Batch Card
        PremiumCard(
            isGlass = true,
            modifier = Modifier.clickable { onViewGrowth() }
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = activeBatch?.breed ?: "No Active Batch",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    if (activeBatch != null) {
                        Surface(
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = activeBatch.currentInstar,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.Bottom) {
                    val daysCount = if (activeBatch != null) {
                        val diff = System.currentTimeMillis() - activeBatch.startDate
                        TimeUnit.MILLISECONDS.toDays(diff).toInt() + 1
                    } else 0
                    
                    Text(
                        text = daysCount.toString(),
                        style = MaterialTheme.typography.displayMedium.copy(
                            fontWeight = FontWeight.Black,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    Text(
                        text = " Days Count",
                        modifier = Modifier.padding(bottom = 12.dp),
                        style = MaterialTheme.typography.bodyLarge.copy(color = secondaryTextColor)
                    )
                }
            }
        }

        // Climate Status Card
        PremiumCard(modifier = Modifier.clickable { onViewClimateDial() }) {
            Column(modifier = Modifier.padding(24.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "CLIMATE STATUS",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = secondaryTextColor,
                            letterSpacing = 1.sp
                        )
                    )
                    StatusGlowBadge(status = status, color = statusColor)
                }
                Spacer(modifier = Modifier.height(24.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    ClimateMetric(
                        icon = Icons.Default.Thermostat,
                        label = "Temperature",
                        value = "${temp.toInt()}°C",
                        modifier = Modifier.weight(1f).clickable { onViewClimateHistory() }
                    )
                    ClimateMetric(
                        icon = Icons.Default.WaterDrop,
                        label = "Humidity",
                        value = "${hum.toInt()}%",
                        modifier = Modifier.weight(1f).clickable { onViewClimateHistory() }
                    )
                }
            }
        }

        // Quick Actions Grid
        Text(
            text = "QUICK TOOLS",
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = secondaryTextColor,
                letterSpacing = 1.sp
            ),
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            QuickActionItem(Icons.Default.Cloud, "Weather", onViewWeather, Modifier.weight(1f))
            QuickActionItem(Icons.Default.AutoAwesome, "AI Insights", onViewInsights, Modifier.weight(1f))
            QuickActionItem(Icons.Default.Mic, "Assistant", onViewVoice, Modifier.weight(1f))
            QuickActionItem(Icons.Default.CameraAlt, "Detect", onViewCamera, Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Advice Card
        PremiumCard(
            containerColor = statusColor.copy(alpha = 0.05f),
            modifier = Modifier.clickable { onEnterClimate() }
        ) {
            Row(
                modifier = Modifier.padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.size(44.dp),
                    shape = CircleShape,
                    color = statusColor.copy(alpha = 0.2f)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        val adviceIcon = when(status.lowercase()) {
                            "danger" -> Icons.Default.Warning
                            "safe" -> Icons.Default.CheckCircle
                            else -> Icons.Default.TipsAndUpdates
                        }
                        Icon(adviceIcon, contentDescription = null, tint = statusColor)
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "SMART ADVICE",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = statusColor,
                            letterSpacing = 1.sp
                        )
                    )
                    Text(
                        text = advice,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                        maxLines = 2
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Bottom Actions
        PremiumButton(
            text = "Start New Batch",
            onClick = onStartNewBatch
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            PremiumOutlinedButton(
                text = "History",
                onClick = onViewHistory,
                modifier = Modifier.weight(1f)
            )
            PremiumOutlinedButton(
                text = "Harvest",
                onClick = onViewHarvest,
                modifier = Modifier.weight(1f)
            )
        }
        
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun QuickActionItem(icon: ImageVector, label: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    PremiumCard(
        modifier = modifier.height(100.dp).clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = label, 
                style = MaterialTheme.typography.labelSmall, 
                fontWeight = FontWeight.Bold, 
                color = if (isSystemInDarkTheme()) TextGreyDark else TextGrey
            )
        }
    }
}

@Composable
fun ClimateMetric(icon: ImageVector, label: String, value: String, modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.08f), CircleShape)
                .scale(scale),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = label, 
                style = MaterialTheme.typography.labelSmall, 
                color = if (isSystemInDarkTheme()) TextGreyDark else TextGrey
            )
            Text(text = value, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
        }
    }
}

@Composable
fun StatusGlowBadge(status: String, color: Color) {
    Surface(
        color = color.copy(alpha = 0.15f),
        shape = CircleShape,
        border = androidx.compose.foundation.BorderStroke(1.dp, color.copy(alpha = 0.5f))
    ) {
        Text(
            text = status.uppercase(),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelSmall.copy(
                color = color,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 1.sp
            )
        )
    }
}
