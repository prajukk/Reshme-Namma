package com.example.reshme_namma_pride.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
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

@Composable
fun StartBatchScreen(onStart: (String, String) -> Unit) {
    var breed by remember { mutableStateOf("Bivoltine") }
    var startDate by remember { mutableStateOf("Oct 24, 2023") }
    var expanded by remember { mutableStateOf(false) }

    val breeds = listOf("Bivoltine", "Multivoltine", "Cross Breed")
    
    val isDark = isSystemInDarkTheme()
    val secondaryTextColor = if (isDark) TextGreyDark else TextGrey

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        
        Text(
            text = "Start New Batch 🌱",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary
            )
        )
        Text(
            text = "Begin a new silkworm rearing cycle",
            style = MaterialTheme.typography.bodyMedium.copy(color = secondaryTextColor)
        )
        
        Spacer(modifier = Modifier.height(48.dp))

        // Breed Selection
        Text(
            text = "SELECT BREED",
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                letterSpacing = 1.sp
            ),
            modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
        )
        Box {
            PremiumCard(
                modifier = Modifier.clickable { expanded = true }
            ) {
                Row(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = breed,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown, 
                        contentDescription = null, 
                        tint = secondaryTextColor
                    )
                }
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                breeds.forEach { item ->
                    DropdownMenuItem(
                        text = { 
                            Text(
                                text = item,
                                color = MaterialTheme.colorScheme.onSurface
                            ) 
                        },
                        onClick = {
                            breed = item
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Start Date Selection
        Text(
            text = "START DATE",
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                letterSpacing = 1.sp
            ),
            modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
        )
        PremiumCard {
            Row(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = startDate,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        PremiumButton(
            text = "Start Batch",
            onClick = { onStart(breed, startDate) }
        )
        Spacer(modifier = Modifier.height(48.dp))
    }
}
