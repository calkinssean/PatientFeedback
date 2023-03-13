package com.seancalkins.patient_feedback.ui.summary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.seancalkins.patient_feedback.extensions.topPadding
import com.seancalkins.patient_feedback.models.TodoItem
import com.seancalkins.patient_feedback.presentation.FeedbackViewModel
import com.seancalkins.patient_feedback.ui.composables.CustomToolbar
import com.seancalkins.patient_feedback.ui.composables.FeedbackCircularProgressIndicator
import com.seancalkins.patient_feedback.ui.composables.RoundedButton
import kotlinx.coroutines.launch

@Composable
fun SummaryScreen(
    viewModel: FeedbackViewModel = hiltViewModel(),
    onSubmitSuccess: () -> Unit
) {
    val submitSuccess = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val submitting = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CustomToolbar(title = "Summary")
        },
        content = {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                if (submitting.value) {
                    FeedbackCircularProgressIndicator(it)
                } else {
                    Text(
                        text = "Thanks again! Here's what we heard:",
                        style = MaterialTheme.typography.body1
                    )
                    Text(
                        text = "Your recommendation of Dr. ${viewModel.todoItem?.doctorFamilyName()} was a ${viewModel.feedback.doctorRecommendation.toInt()} and they did a ${viewModel.doctorPerformance()} job of explaining how to manage your diagnosis.",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.topPadding(10.dp)
                    )
                    Text(
                        text = "Your thoughts on being diagnosed with ${viewModel.todoItem?.diagnosisName()} are:",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.topPadding(10.dp)
                    )
                    Text(
                        text = viewModel.feedback.diagnosisFeedback,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.topPadding(10.dp)
                    )
                    RoundedButton(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .topPadding(20.dp),
                        text = "Submit",
                        onTapped = {
                            scope.launch {
                                viewModel.submitFeedback()
                            }

//                            onSubmitSuccess()
                        }
                    )
                }
            }
        }
    )
}

