package com.example.reshme_namma_pride.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reshme_namma_pride.ui.components.PremiumCard
import com.example.reshme_namma_pride.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GrowthTrackerScreen(onBack: () -> Unit) {
    val stages = listOf("Egg", "1st Instar", "2nd Instar", "3rd Instar", "4th Instar", "5th Instar", "Cocoon")
    val currentStageIndex = 3 // 3rd Instar

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Growth Tracker", fontWeight = FontWeight.Bold) },
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
            // Horizontal Stepper
            PremiumCard {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Current Progress",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    GrowthStepper(
                        stages = stages,
                        currentStageIndex = currentStageIndex
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    LinearProgressIndicator(
                        progress = { (currentStageIndex + 1).toFloat() / stages.size },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .clip(RoundedCornerShape(5.dp)),
                        color = GreenPrimary,
                        trackColor = GreenPrimary.copy(alpha = 0.1f),
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Current Stage Detail Card
            PremiumCard {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            modifier = Modifier.size(60.dp),
                            shape = RoundedCornerShape(16.dp),
                            color = GreenPrimary.copy(alpha = 0.1f)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                // Placeholder for image/illustration
                                Text("🐛", fontSize = 32.sp)
                            }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = stages[currentStageIndex],
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.ExtraBold,
                                color = GreenPrimary
                            )
                            Text(
                                text = "Active since 3 days",
                                style = MaterialTheme.typography.bodyMedium,
                                color = TextGrey
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Tip Card
            PremiumCard(containerColor = GreenPrimary.copy(alpha = 0.05f)) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Lightbulb, contentDescription = null, tint = GreenPrimary)
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "GROWTH TIP",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = GreenPrimary
                        )
                        Text(
                            text = "Increase feeding frequency at this stage for optimal growth.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GrowthStepper(stages: List<String>, currentStageIndex: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        stages.forEachIndexed { index, _ ->
            val isCompleted = index < currentStageIndex
            val isCurrent = index == currentStageIndex
            
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(
                            color = when {
                                isCompleted -> GreenPrimary
                                isCurrent -> GreenPrimary.copy(alpha = 0.2f)
                                else -> Color.LightGray.copy(alpha = 0.3f)
                            },
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (isCompleted) {
                        Icon(Icons.Default.Check, contentDescription = null, tint = White, modifier = Modifier.size(16.dp))
                    } else if (isCurrent) {
                        Box(modifier = Modifier.size(12.dp).background(GreenPrimary, CircleShape))
                    }
                }
            }
            
            if (index < stages.size - 1) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(2.dp)
                        .background(
                            if (index < currentStageIndex) GreenPrimary else Color.LightGray.copy(alpha = 0.3f)
                        )
                )
            }
        }
    }
}
