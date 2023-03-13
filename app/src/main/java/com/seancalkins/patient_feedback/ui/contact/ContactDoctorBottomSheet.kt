package com.seancalkins.patient_feedback.ui.contact

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.seancalkins.patient_feedback.extensions.topPadding
import com.seancalkins.patient_feedback.models.Doctor
import com.seancalkins.patient_feedback.ui.composables.RoundedButton

@Composable
@ExperimentalMaterialApi
fun ContactDoctorBottomSheet(doctor: Doctor?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(16.dp)
    ) {
        Text(
            text = "Contact Dr. ${doctor?.familyName()}",
            style = MaterialTheme.typography.body1,
        )
        RoundedButton(
            modifier = Modifier
                .topPadding(80.dp)
                .align(Alignment.CenterHorizontally),
            text = "Call",
            onTapped = {
                // Call Doctor
            }
        )
        RoundedButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .topPadding(20.dp),
            text = "Email",
            onTapped = {
                // Email Doctor
            }
        )
    }
}
