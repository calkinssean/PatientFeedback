package com.seancalkins.patient_feedback.presentation.common

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun CustomToolbar(
    title: String
) {
    TopAppBar(
        title = { Text(text = title, style = MaterialTheme.typography.body1) }
    )
}