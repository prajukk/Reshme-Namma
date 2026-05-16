package com.example.reshme_namma_pride.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.reshme_namma_pride.ui.components.PremiumCard
import com.example.reshme_namma_pride.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onBack: () -> Unit) {
    var notificationsEnabled by remember { mutableStateOf(true) }
    var language by remember { mutableStateOf("English") }
    var unitCelsius by remember { mutableStateOf(true) }
    var darkTheme by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings", fontWeight = FontWeight.Bold) },
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
            SettingHeader("Preferences")
            
            PremiumCard {
                Column {
                    SettingToggleItem(
                        icon = Icons.Default.Notifications,
                        title = "Notifications",
                        checked = notificationsEnabled,
                        onCheckedChange = { notificationsEnabled = it }
                    )
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 20.dp), color = BackgroundGrey)
                    SettingClickItem(
                        icon = Icons.Default.Language,
                        title = "Language",
                        value = language,
                        onClick = { /* TODO: Show language picker */ }
                    )
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 20.dp), color = BackgroundGrey)
                    SettingToggleItem(
                        icon = Icons.Default.DeviceThermostat,
                        title = "Use Celsius (°C)",
                        checked = unitCelsius,
                        onCheckedChange = { unitCelsius = it }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            SettingHeader("Appearance")
            
            PremiumCard {
                Column {
                    SettingToggleItem(
                        icon = Icons.Default.DarkMode,
                        title = "Dark Mode",
                        checked = darkTheme,
                        onCheckedChange = { darkTheme = it }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            SettingHeader("About")
            
            PremiumCard {
                Column {
                    SettingClickItem(
                        icon = Icons.Default.Info,
                        title = "Version",
                        value = "1.0.0",
                        onClick = {}
                    )
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 20.dp), color = BackgroundGrey)
                    SettingClickItem(
                        icon = Icons.Default.Description,
                        title = "Terms of Service",
                        onClick = {}
                    )
                }
            }
        }
    }
}

@Composable
fun SettingHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.Bold,
        color = GreenPrimary,
        modifier = Modifier.padding(start = 4.dp, bottom = 12.dp)
    )
}

@Composable
fun SettingToggleItem(
    icon: ImageVector,
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = GreenPrimary)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = title, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium)
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = White,
                checkedTrackColor = GreenPrimary,
                uncheckedThumbColor = White,
                uncheckedTrackColor = Color.LightGray
            )
        )
    }
}

@Composable
fun SettingClickItem(
    icon: ImageVector,
    title: String,
    value: String? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = GreenPrimary)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = title, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (value != null) {
                Text(text = value, style = MaterialTheme.typography.bodyMedium, color = TextGrey)
                Spacer(modifier = Modifier.width(8.dp))
            }
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = Color.LightGray)
        }
    }
}
