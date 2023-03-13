package com.seancalkins.patient_feedback.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.seancalkins.patient_feedback.extensions.topPadding
import com.seancalkins.patient_feedback.models.TodoItem
import com.seancalkins.patient_feedback.presentation.TodoListViewModel
import com.seancalkins.patient_feedback.ui.composables.CustomToolbar
import com.seancalkins.patient_feedback.ui.composables.FeedbackCircularProgressIndicator
import com.seancalkins.patient_feedback.ui.composables.RoundedButton
import com.seancalkins.patient_feedback.ui.todo_items.TodoItemScreenList

@Composable
fun HomeScreen(
    onNextTapped: () -> Unit
) {
    Content(onNextTapped)
}

@Preview
@Composable
fun Content(onNextTapped: () -> Unit = {}) {
    Scaffold(
        topBar =  {
            CustomToolbar("Home")
        },
        content =  {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Welcome To Tendo!",
                    style = MaterialTheme.typography.h2,
                    textAlign = TextAlign.Center
                )
                RoundedButton(
                    modifier = Modifier
                        .topPadding(200.dp)
                        .align(Alignment.CenterHorizontally),
                    text = "View Todo Items",
                    onTapped = {
                        onNextTapped()
                    }
                )

            }
        }
    )
}