package com.seancalkins.patient_feedback.ui.diagnosis

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.seancalkins.patient_feedback.extensions.topPadding
import com.seancalkins.patient_feedback.models.TodoItem
import com.seancalkins.patient_feedback.presentation.FeedbackViewModel
import com.seancalkins.patient_feedback.ui.composables.CustomToolbar
import com.seancalkins.patient_feedback.ui.composables.RoundedButton

@Composable
fun DiagnosisScreen(
    viewModel: FeedbackViewModel = hiltViewModel(),
    onDiagnosisResponse: (Boolean) -> Unit
) {
    Scaffold(
        topBar =  {
            CustomToolbar("Diagnosis")
        },
        content = {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Thank you. You were diagnosed with ${viewModel.todoItem?.diagnosisName()}. Did Dr. ${viewModel.todoItem?.doctorFamilyName()} explain how to manage this diagnosis in a way you could understand?",
                    style = MaterialTheme.typography.body1
                )
                RoundedButton(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .topPadding(200.dp),
                    text = "Yes",
                    onTapped = {
                        viewModel.feedback.understoodDiagnosis = true
                        onDiagnosisResponse(true)
                    }
                )
                RoundedButton(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .topPadding(20.dp),
                    text = "No",
                    onTapped = {
                        viewModel.feedback.understoodDiagnosis = false
                        onDiagnosisResponse(false)
                    }
                )
            }
        }
    )
}