package com.seancalkins.patient_feedback.presentation.diagnosis

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.seancalkins.patient_feedback.common.extensions.topPadding
import com.seancalkins.patient_feedback.presentation.feedback.FeedbackViewModel
import com.seancalkins.patient_feedback.presentation.common.CustomToolbar
import com.seancalkins.patient_feedback.presentation.common.RoundedButton

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
                    text = "Thank you. You were diagnosed with ${viewModel.diagnosis}. Did Dr. ${viewModel.doctorName} explain how to manage this diagnosis in a way you could understand?",
                    style = MaterialTheme.typography.body1
                )
                RoundedButton(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .topPadding(200.dp),
                    text = "Yes",
                    onTapped = {
                        viewModel.understoodDiagnosis = true
                        onDiagnosisResponse(true)
                    }
                )
                RoundedButton(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .topPadding(20.dp),
                    text = "No",
                    onTapped = {
                        viewModel.understoodDiagnosis = false
                        onDiagnosisResponse(false)
                    }
                )
            }
        }
    )
}