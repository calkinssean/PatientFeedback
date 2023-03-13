package com.seancalkins.patient_feedback.ui.diagnosis

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.seancalkins.patient_feedback.extensions.topPadding
import com.seancalkins.patient_feedback.models.TodoItem
import com.seancalkins.patient_feedback.presentation.FeedbackViewModel
import com.seancalkins.patient_feedback.ui.composables.CustomToolbar
import com.seancalkins.patient_feedback.ui.composables.RoundedButton

@Composable
fun DiagnosisFeedbackScreen(
    viewModel: FeedbackViewModel = hiltViewModel(),
    onNextTapped: () -> Unit
) {
    val textState = remember { mutableStateOf(viewModel.feedback.diagnosisFeedback) }
    val focusManager = LocalFocusManager.current
    Scaffold(
        topBar = {
            CustomToolbar(title = "Feedback")
        },
        content = {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "We appreciate the feedback, one last question: how do you feel about being diagnosed with ${viewModel.todoItem?.diagnosisName()}?",
                    style = MaterialTheme.typography.body1
                )
                OutlinedTextField(
                    modifier = Modifier
                        .topPadding(20.dp)
                        .fillMaxWidth()
                        .height(200.dp),
                    label = { Text(text = "Feedback", style = MaterialTheme.typography.caption) },
                    placeholder = {
                        Text(
                            text = "It's actually really cool/fun for me",
                            style = MaterialTheme.typography.body1
                        )
                    },
                    value = textState.value,
                    onValueChange = {
                        textState.value = it
                    },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    )
                )
                RoundedButton(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .topPadding(40.dp),
                    text = "Next",
                    onTapped = {
                        viewModel.feedback.diagnosisFeedback = textState.value
                        onNextTapped()
                    }
                )
            }
        }
    )
}