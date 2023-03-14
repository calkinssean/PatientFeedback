package com.seancalkins.patient_feedback.common.extensions

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.buttonSize(): Modifier =
    width(250.dp)
        .height(50.dp)

fun Modifier.topPadding(padding: Dp): Modifier =
    padding(PaddingValues(top = padding))