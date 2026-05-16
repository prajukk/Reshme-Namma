package com.example.reshme_namma_pride

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.*
import com.example.reshme_namma_pride.ui.screens.*
import com.example.reshme_namma_pride.ui.theme.ReshmeNammaPrideTheme
import com.example.reshme_namma_pride.ui.viewmodel.BatchViewModel
import com.example.reshme_namma_pride.ui.viewmodel.BatchViewModelFactory

class MainActivity : ComponentActivity() {
    private val batchViewModel: BatchViewModel by viewModels {
        BatchViewModelFactory((application as ReshmeApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReshmeNammaPrideTheme {
                var currentScreen by remember { mutableStateOf("dashboard") }
                var analysisStatus by remember { mutableStateOf("Safe") }
                var analysisAdvice by remember { mutableStateOf("Optimal rearing conditions maintained.") }
                var currentTemp by remember { mutableStateOf(26f) }
                var currentHum by remember { mutableStateOf(72f) }

                val activeBatch by batchViewModel.activeBatch.collectAsState()
                
                when (currentScreen) {
                    "dashboard" -> DashboardScreen(
                        temp = currentTemp,
                        hum = currentHum,
                        status = analysisStatus,
                        advice = analysisAdvice,
                        activeBatch = activeBatch,
                        onStartNewBatch = { currentScreen = "start_batch" },
                        onViewClimateDial = { currentScreen = "climate_dial" },
                        onEnterClimate = { currentScreen = "climate_entry" },
                        onViewHarvest = { currentScreen = "harvest_timer" },
                        onViewHistory = { currentScreen = "batch_history" },
                        onViewAlerts = { currentScreen = "alerts" },
                        onViewSettings = { currentScreen = "settings" },
                        onViewInsights = { currentScreen = "insights" },
                        onViewWeather = { currentScreen = "weather" },
                        onViewVoice = { currentScreen = "voice" },
                        onViewCamera = { currentScreen = "camera" },
                        onViewGrowth = { currentScreen = "growth_tracker" },
                        onViewClimateHistory = { currentScreen = "climate_history" }
                    )
                    "start_batch" -> StartBatchScreen(
                        onStart = { breed, _ -> 
                            batchViewModel.insertBatch(breed)
                            currentScreen = "dashboard" 
                        }
                    )
                    "climate_entry" -> ClimateEntryScreen(
                        onAnalyze = { temp, hum ->
                            currentTemp = temp
                            currentHum = hum
                            val result = performAnalysis(temp, hum)
                            analysisStatus = result.first
                            analysisAdvice = result.second
                            currentScreen = "smart_advice"
                        }
                    )
                    "smart_advice" -> SmartAdviceScreen(
                        status = analysisStatus,
                        advice = analysisAdvice,
                        onBack = { currentScreen = "dashboard" }
                    )
                    "climate_dial" -> ClimateDialScreen(
                        currentValue = currentTemp,
                        onBack = { currentScreen = "dashboard" }
                    )
                    "harvest_timer" -> HarvestTimerScreen(
                        onBack = { currentScreen = "dashboard" }
                    )
                    "climate_history" -> ClimateHistoryScreen(
                        onBack = { currentScreen = "dashboard" }
                    )
                    "growth_tracker" -> GrowthTrackerScreen(
                        onBack = { currentScreen = "dashboard" }
                    )
                    "alerts" -> AlertsScreen(
                        onBack = { currentScreen = "dashboard" }
                    )
                    "batch_history" -> {
                        val allBatches by batchViewModel.allBatches.collectAsState()
                        BatchHistoryScreen(
                            batches = allBatches,
                            onBack = { currentScreen = "dashboard" }
                        )
                    }
                    "insights" -> PerformanceInsightsScreen(
                        onBack = { currentScreen = "dashboard" }
                    )
                    "settings" -> SettingsScreen(
                        onBack = { currentScreen = "dashboard" }
                    )
                    "weather" -> WeatherIntegrationScreen(
                        onBack = { currentScreen = "dashboard" }
                    )
                    "voice" -> VoiceAssistantScreen(
                        onBack = { currentScreen = "dashboard" }
                    )
                    "camera" -> CameraDetectionScreen(
                        onBack = { currentScreen = "dashboard" }
                    )
                }
            }
        }
    }

    private fun performAnalysis(temp: Float, hum: Float): Pair<String, String> {
        return when {
            temp > 30f || hum > 85f -> {
                "Danger" to "Critical levels detected! Immediately increase cross-ventilation and use fans. High humidity at high temperatures can lead to Flacherie disease."
            }
            temp > 27f || hum > 75f -> {
                "Caution" to "Conditions are slightly above optimal. Open windows and increase ventilation to lower the temperature. Ensure adequate spacing between rearing trays."
            }
            temp < 22f || hum < 60f -> {
                "Caution" to "Conditions are too cool or dry. Close windows to retain heat or use a humidifier/wet gunny bags to increase moisture levels."
            }
            else -> {
                "Safe" to "Optimal rearing conditions maintained. Continue regular leaf feeding schedule and maintain current ventilation."
            }
        }
    }
}
