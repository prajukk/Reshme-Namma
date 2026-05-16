package com.example.reshme_namma_pride.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reshme_namma_pride.ui.components.PremiumCard
import com.example.reshme_namma_pride.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClimateHistoryScreen(onBack: () -> Unit) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Day", "Week", "Month")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Climate History", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
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
            // Toggle
            SingleChoiceSegmentedButtonRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                tabs.forEachIndexed { index, label ->
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(index = index, count = tabs.size),
                        onClick = { selectedTab = index },
                        selected = index == selectedTab,
                        label = { Text(label) },
                        colors = SegmentedButtonDefaults.colors(
                            activeContainerColor = GreenPrimary,
                            activeContentColor = White,
                            inactiveContainerColor = White,
                            inactiveContentColor = TextGrey
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Graph Card
            PremiumCard {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Temperature Trend (°C)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Simple Line Graph Representation
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
                        LineGraph(
                            dataPoints = listOf(24f, 26f, 25f, 28f, 30f, 27f, 25f),
                            color = GreenPrimary
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Mon", style = MaterialTheme.typography.labelSmall, color = TextGrey)
                        Text("Tue", style = MaterialTheme.typography.labelSmall, color = TextGrey)
                        Text("Wed", style = MaterialTheme.typography.labelSmall, color = TextGrey)
                        Text("Thu", style = MaterialTheme.typography.labelSmall, color = TextGrey)
                        Text("Fri", style = MaterialTheme.typography.labelSmall, color = TextGrey)
                        Text("Sat", style = MaterialTheme.typography.labelSmall, color = TextGrey)
                        Text("Sun", style = MaterialTheme.typography.labelSmall, color = TextGrey)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Summary Stats
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                StatCard(label = "Avg Temp", value = "26.5°C", color = GreenPrimary, modifier = Modifier.weight(1f))
                StatCard(label = "Avg Humidity", value = "72%", color = GreenLight, modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Insight Card
            PremiumCard(containerColor = OrangeAccent.copy(alpha = 0.05f)) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Info, contentDescription = null, tint = OrangeAccent)
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "AI INSIGHT",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = OrangeAccent
                        )
                        Text(
                            text = "Temperature was high for 3 days — improve ventilation to maintain cocoon quality.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StatCard(label: String, value: String, color: Color, modifier: Modifier = Modifier) {
    PremiumCard(modifier = modifier) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = label, style = MaterialTheme.typography.labelMedium, color = TextGrey)
            Text(text = value, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = color)
        }
    }
}

@Composable
fun LineGraph(dataPoints: List<Float>, color: Color) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height
        val maxVal = dataPoints.maxOrNull() ?: 1f
        val minVal = dataPoints.minOrNull() ?: 0f
        val range = maxVal - minVal
        
        val path = Path()
        val stepX = width / (dataPoints.size - 1)
        
        dataPoints.forEachIndexed { index, value ->
            val x = index * stepX
            val y = height - ((value - minVal) / range) * height
            
            if (index == 0) {
                path.moveTo(x, y)
            } else {
                path.lineTo(x, y)
            }
        }
        
        drawPath(
            path = path,
            color = color,
            style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
        )
        
        // Fill area
        val fillPath = Path().apply {
            addPath(path)
            lineTo(width, height)
            lineTo(0f, height)
            close()
        }
        
        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = listOf(color.copy(alpha = 0.3f), Color.Transparent)
            )
        )
        
        // Draw points
        dataPoints.forEachIndexed { index, value ->
            val x = index * stepX
            val y = height - ((value - minVal) / range) * height
            drawCircle(
                color = color,
                radius = 4.dp.toPx(),
                center = androidx.compose.ui.geometry.Offset(x, y)
            )
            drawCircle(
                color = White,
                radius = 2.dp.toPx(),
                center = androidx.compose.ui.geometry.Offset(x, y)
            )
        }
    }
}
