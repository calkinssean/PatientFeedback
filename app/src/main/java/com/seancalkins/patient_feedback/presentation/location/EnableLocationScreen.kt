package com.seancalkins.patient_feedback.presentation.location

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.IntentSender
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsResponse
import com.google.android.gms.tasks.Task
import com.seancalkins.patient_feedback.common.extensions.topPadding
import com.seancalkins.patient_feedback.presentation.common.CustomToolbar
import com.seancalkins.patient_feedback.presentation.common.RoundedButton
import com.seancalkins.patient_feedback.presentation.feedback.FeedbackViewModel

@Composable
fun EnableLocationScreen(
    viewModel: FeedbackViewModel = hiltViewModel(),
    onNextTapped: () -> Unit
) {

    val context: Context = LocalContext.current

    val settingResultRequest = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { activityResult ->
        if (activityResult.resultCode == RESULT_OK)
            onNextTapped()
        else {
            Toast.makeText(context, "Please? :(", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            CustomToolbar("Diagnosis")
        },
        content = {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Before you go, will you let us track your location in case you skip on your bill?",
                    style = MaterialTheme.typography.body1
                )
                RoundedButton(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .topPadding(200.dp),
                    text = "Enable Location Services",
                    onTapped = {
                        checkLocationSetting(
                            context = context,
                            onDisabled = { intentSenderRequest ->
                                settingResultRequest.launch(intentSenderRequest)
                            },
                            onEnabled = {
                                onNextTapped()
                            }
                        )
                    }
                )
            }
        }
    )
}

fun checkLocationSetting(
    context: Context,
    onDisabled: (IntentSenderRequest) -> Unit,
    onEnabled: () -> Unit
) {

    val locationRequest = LocationRequest.create().apply {
        interval = 1000
        fastestInterval = 1000
        priority = PRIORITY_HIGH_ACCURACY
    }

    val client: com.google.android.gms.location.SettingsClient = LocationServices.getSettingsClient(context)
    val builder: LocationSettingsRequest.Builder = LocationSettingsRequest
        .Builder()
        .addLocationRequest(locationRequest)

    val gpsSettingTask: Task<LocationSettingsResponse> =
        client.checkLocationSettings(builder.build())

    gpsSettingTask.addOnSuccessListener { onEnabled() }
    gpsSettingTask.addOnFailureListener { exception ->
        if (exception is ResolvableApiException) {
            try {
                val intentSenderRequest = IntentSenderRequest
                    .Builder(exception.resolution)
                    .build()
                onDisabled(intentSenderRequest)
            } catch (e: IntentSender.SendIntentException) {
                Log.e("EnableLocationScreen", "Exception: $e")
            }
        }
    }

}