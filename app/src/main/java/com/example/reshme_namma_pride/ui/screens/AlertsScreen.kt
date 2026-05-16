package com.example.reshme_namma_pride.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reshme_namma_pride.ui.components.PremiumCard
import com.example.reshme_namma_pride.ui.theme.*

data class AlertItem(
    val id: Int,
    val title: String,
    val description: String,
    val time: String,
    val type: AlertType
)

enum class AlertType {
    DANGER, WARNING, SUCCESS
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertsScreen(onBack: () -> Unit) {
    val alerts = listOf(
        AlertItem(1, "High Temperature", "Temperature reached 32°C. Open windows for ventilation.", "10 mins ago", AlertType.DANGER),
        AlertItem(2, "Low Humidity", "Humidity dropped to 60%. Lightly spray water on walls.", "1 hour ago", AlertType.WARNING),
        AlertItem(3, "Safe Conditions", "Optimal growth conditions restored.", "3 hours ago", AlertType.SUCCESS),
        AlertItem(4, "Feeding Time", "Silkworms are entering 4th instar. Increase leaf supply.", "5 hours ago", AlertType.WARNING)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Alerts & Notifications", fontWeight = FontWeight.Bold) },
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(alerts) { alert ->
                AlertCard(alert)
            }
        }
    }
}

@Composable
fun AlertCard(alert: AlertItem) {
    val isDark = isSystemInDarkTheme()
    val secondaryTextColor = if (isDark) TextGreyDark else TextGrey

    val color = when (alert.type) {
        AlertType.DANGER -> RedAccent
        AlertType.WARNING -> OrangeAccent
        AlertType.SUCCESS -> MaterialTheme.colorScheme.primary
    }
    
    val icon = when (alert.type) {
        AlertType.DANGER -> Icons.Default.Warning
        AlertType.WARNING -> Icons.Default.Warning
        AlertType.SUCCESS -> Icons.Default.CheckCircle
    }

    PremiumCard {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Surface(
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                color = color.copy(alpha = 0.15f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(20.dp))
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = alert.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = color
                    )
                    Text(
                        text = alert.time,
                        style = MaterialTheme.typography.labelSmall,
                        color = secondaryTextColor
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = alert.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}
