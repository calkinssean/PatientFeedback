package com.seancalkins.patient_feedback.ui.composables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seancalkins.patient_feedback.extensions.buttonSize

@Preview
@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    text: String = "Button Text",
    onTapped: () -> Unit = {},
) {
    Button(
        modifier = modifier.buttonSize(),
        onClick = {
            onTapped()
        },
        shape = RoundedCornerShape(25.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body1
        )
    }
}