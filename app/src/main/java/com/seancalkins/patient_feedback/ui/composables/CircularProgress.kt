package com.seancalkins.patient_feedback.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier

@Composable
fun FeedbackCircularProgressIndicator(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = paddingValues.calculateTopPadding())
    ) {
        Spacer(Modifier.height(16.dp))
        androidx.compose.material.CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}