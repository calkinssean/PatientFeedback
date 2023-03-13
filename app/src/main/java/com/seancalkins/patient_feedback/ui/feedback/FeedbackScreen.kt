package com.seancalkins.patient_feedback.ui.feedback

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.seancalkins.patient_feedback.extensions.topPadding
import com.seancalkins.patient_feedback.models.TodoItem
import com.seancalkins.patient_feedback.presentation.FeedbackViewModel
import com.seancalkins.patient_feedback.ui.composables.CustomToolbar
import com.seancalkins.patient_feedback.ui.composables.RoundedButton

@Composable
fun FeedbackScreen(
    viewModel: FeedbackViewModel = hiltViewModel(),
    onNextTapped: () -> Unit
) {

    val doctorRecommendation = remember { mutableStateOf(viewModel.feedback.doctorRecommendation) }

    Scaffold(
        topBar = {
            CustomToolbar(title = "Feedback")
        },
        content = {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Hi ${viewModel.todoItem?.patientGivenName()}, on a scale of 1-10, would you recommend Dr. ${viewModel.todoItem?.doctorFamilyName()} to a friend or family member?",
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = doctorRecommendation.value.toInt().toString(),
                    modifier = Modifier.align(Alignment.End)
                )
                Slider(
                    value = doctorRecommendation.value,
                    onValueChange = {
                        doctorRecommendation.value = it
                    },
                    valueRange = 1f..10f,
                    steps = 8
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(40.dp)
                ) {
                    Text(
                        text = "1 = Would not recommend",
                        style = MaterialTheme.typography.caption,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "10 = Would strongly Recommend",
                        style = MaterialTheme.typography.caption,
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(1f)
                    )
                }
                RoundedButton(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .topPadding(200.dp),
                    text = "Next",
                    onTapped = {
                        viewModel.feedback.doctorRecommendation = doctorRecommendation.value
                        onNextTapped()
                    }
                )
            }
        }
    )

}
