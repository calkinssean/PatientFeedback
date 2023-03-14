package com.seancalkins.patient_feedback.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import com.seancalkins.patient_feedback.presentation.ui.theme.PatientFeedbackTheme
import com.seancalkins.patient_feedback.presentation.util.NavConfigurationUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PatientFeedbackTheme {
                NavConfigurationUtil()
            }
        }
    }
}
