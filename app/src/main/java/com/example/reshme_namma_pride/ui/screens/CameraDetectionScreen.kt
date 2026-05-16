package com.example.reshme_namma_pride.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reshme_namma_pride.ui.components.PremiumButton
import com.example.reshme_namma_pride.ui.components.PremiumCard
import com.example.reshme_namma_pride.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraDetectionScreen(onBack: () -> Unit) {
    var isAnalyzing by remember { mutableStateOf(false) }
    var resultFound by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Disease Detection", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = Black // Camera viewfinder background
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            // Camera Viewfinder Placeholder
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.DarkGray.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                if (!resultFound) {
                    // Scanning square
                    Box(
                        modifier = Modifier
                            .size(280.dp)
                            .border(2.dp, GreenPrimary, RoundedCornerShape(24.dp))
                    )
                }
            }

            // UI Overlays
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Point camera at silkworms",
                    color = White,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.background(Black.copy(alpha = 0.5f), CircleShape).padding(horizontal = 16.dp, vertical = 8.dp)
                )

                if (resultFound) {
                    PremiumCard(containerColor = White) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Warning, contentDescription = null, tint = RedAccent)
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = "Possible Infection Detected",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = RedAccent
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Signs of Grasserie detected. Isolate affected worms and reduce humidity immediately.",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            PremiumButton(
                                text = "View Treatment Plan",
                                onClick = { /* TODO */ }
                            )
                        }
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.FlashOn, contentDescription = "Flash", tint = White)
                        }
                        
                        // Capture Button
                        Surface(
                            modifier = Modifier.size(80.dp),
                            shape = CircleShape,
                            color = White,
                            onClick = { 
                                isAnalyzing = true
                                // Simulate detection delay
                                resultFound = true
                                isAnalyzing = false
                            }
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Box(
                                    modifier = Modifier
                                        .size(60.dp)
                                        .border(2.dp, Black, CircleShape)
                                )
                            }
                        }

                        IconButton(onClick = {}) {
                            Icon(Icons.Default.Camera, contentDescription = "Switch", tint = White)
                        }
                    }
                }
            }
            
            if (isAnalyzing) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = GreenPrimary
                )
            }
        }
    }
}
