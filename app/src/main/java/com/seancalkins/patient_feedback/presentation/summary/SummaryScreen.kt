package com.seancalkins.patient_feedback.presentation.summary

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.seancalkins.patient_feedback.common.extensions.topPadding
import com.seancalkins.patient_feedback.presentation.feedback.FeedbackViewModel
import com.seancalkins.patient_feedback.presentation.common.CustomToolbar
import com.seancalkins.patient_feedback.presentation.common.RoundedButton
import kotlinx.coroutines.launch

@Composable
fun SummaryScreen(
    viewModel: FeedbackViewModel = hiltViewModel(),
    onSubmitSuccess: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val submitting = remember { viewModel.submittingFeedback }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CustomToolbar(title = "Summary")
        },
        content = {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                if (submitting.value) {
                    CircularProgressIndicator()
                } else {
                    Text(
                        text = "Thanks again! Here's what we heard:",
                        style = MaterialTheme.typography.body1
                    )
                    Text(
                        text = "Your recommendation of Dr. ${viewModel.doctorName} was a ${viewModel.doctorRecommendation.toInt()} and they did a ${viewModel.doctorPerformance()} job of explaining how to manage your diagnosis.",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.topPadding(10.dp)
                    )
                    Text(
                        text = "Your thoughts on being diagnosed with ${viewModel.diagnosis} are:",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.topPadding(10.dp)
                    )
                    Text(
                        text = viewModel.diagnosisFeedback,
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
                                if (viewModel.submitFeedback()) {
                                    onSubmitSuccess()
                                } else {
                                    Toast.makeText(context, "Something went wrong, please try again", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    )
                }
            }
        }
    )
}