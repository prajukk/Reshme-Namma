package com.example.reshme_namma_pride.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reshme_namma_pride.ui.components.PremiumCard
import com.example.reshme_namma_pride.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherIntegrationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Weather Integration", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BackgroundGrey)
            )
        },
        containerColor = BackgroundGrey
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Current Weather Card
            PremiumCard(
                containerColor = GreenPrimary
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Mysuru, Karnataka",
                        style = MaterialTheme.typography.titleMedium,
                        color = White.copy(alpha = 0.8f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Icon(
                        Icons.Default.WbSunny,
                        contentDescription = null,
                        tint = OrangeAccent,
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "29°C",
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Black,
                        color = White
                    )
                    Text(
                        text = "Sunny • H:31° L:22°",
                        style = MaterialTheme.typography.bodyLarge,
                        color = White.copy(alpha = 0.9f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Suggestion Card
            PremiumCard(containerColor = OrangeAccent.copy(alpha = 0.1f)) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Cloud, contentDescription = null, tint = OrangeAccent)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Tomorrow temperature will rise — prepare ventilation systems in advance.",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "7-Day Forecast",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = TextGrey,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // Horizontal Forecast Row (Simplified as vertical for now)
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                ForecastItem("Tue", Icons.Default.WbSunny, "30°C", "20%")
                ForecastItem("Wed", Icons.Default.WbSunny, "31°C", "10%")
                ForecastItem("Thu", Icons.Default.Cloud, "28°C", "40%")
                ForecastItem("Fri", Icons.Default.Cloud, "27°C", "60%")
            }
        }
    }
}

@Composable
fun ForecastItem(day: String, icon: ImageVector, temp: String, humidity: String) {
    PremiumCard {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = day, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, modifier = Modifier.width(50.dp))
            Icon(icon, contentDescription = null, tint = GreenPrimary)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Thermostat, contentDescription = null, tint = TextGrey, modifier = Modifier.size(16.dp))
                Text(text = temp, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.WaterDrop, contentDescription = null, tint = TextGrey, modifier = Modifier.size(16.dp))
                Text(text = humidity, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
