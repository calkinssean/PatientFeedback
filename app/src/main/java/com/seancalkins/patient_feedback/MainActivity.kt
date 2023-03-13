package com.seancalkins.patient_feedback

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.seancalkins.patient_feedback.models.TodoItem
import com.seancalkins.patient_feedback.ui.feedback.FeedbackScreen
import com.seancalkins.patient_feedback.ui.theme.PatientFeedbackTheme
import com.seancalkins.patient_feedback.ui.todo_items.TodoItemsScreen
import com.seancalkins.patient_feedback.util.NavConfigurationUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

@Composable
fun DefaultPreview() {
    PatientFeedbackTheme {
    }
}