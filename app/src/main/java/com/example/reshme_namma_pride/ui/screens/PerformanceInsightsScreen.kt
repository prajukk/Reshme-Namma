package com.example.reshme_namma_pride.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reshme_namma_pride.ui.components.PremiumCard
import com.example.reshme_namma_pride.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerformanceInsightsScreen(onBack: () -> Unit) {
    val isDark = isSystemInDarkTheme()
    val secondaryTextColor = if (isDark) TextGreyDark else TextGrey

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AI Insights", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
                    navigationIconContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            Text(
                text = "Performance Overview",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = secondaryTextColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            InsightMetricCard(
                title = "Best conditions achieved",
                value = "92% Consistency",
                icon = Icons.Default.AutoAwesome,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            InsightMetricCard(
                title = "Common mistakes",
                value = "High Humidity (Night)",
                icon = Icons.Default.WarningAmber,
                color = OrangeAccent
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Chart Placeholder
            PremiumCard {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Yield vs Climate",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .background(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), 
                                RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.TrendingUp, 
                                contentDescription = null, 
                                tint = MaterialTheme.colorScheme.primary, 
                                modifier = Modifier.size(48.dp)
                            )
                            Text(
                                text = "Positive Correlation Detected", 
                                color = MaterialTheme.colorScheme.primary, 
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // AI Suggestion
            PremiumCard(containerColor = MaterialTheme.colorScheme.primary) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = "SMART SUGGESTION",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = if (isDark) Black.copy(alpha = 0.7f) else White.copy(alpha = 0.7f)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Maintain humidity between 70–80% for better cocoon quality based on your past 3 successful batches.",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = if (isDark) Black else White
                    )
                }
            }
        }
    }
}

@Composable
fun InsightMetricCard(title: String, value: String, icon: ImageVector, color: Color) {
    val isDark = isSystemInDarkTheme()
    val secondaryTextColor = if (isDark) TextGreyDark else TextGrey

    PremiumCard {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                color = color.copy(alpha = 0.15f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(icon, contentDescription = null, tint = color)
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title, style = MaterialTheme.typography.labelMedium, color = secondaryTextColor)
                Text(
                    text = value, 
                    style = MaterialTheme.typography.titleLarge, 
                    fontWeight = FontWeight.Bold, 
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}
