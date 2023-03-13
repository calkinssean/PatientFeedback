package com.seancalkins.patient_feedback.ui.diagnosis

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.seancalkins.patient_feedback.extensions.topPadding
import com.seancalkins.patient_feedback.models.TodoItem
import com.seancalkins.patient_feedback.presentation.FeedbackViewModel
import com.seancalkins.patient_feedback.ui.composables.CustomToolbar
import com.seancalkins.patient_feedback.ui.composables.RoundedButton
import com.seancalkins.patient_feedback.ui.contact.ContactDoctorBottomSheet
import kotlinx.coroutines.launch

@Composable
@ExperimentalMaterialApi
fun DiagnosisResourcesScreen(
    viewModel: FeedbackViewModel = hiltViewModel(),
    onViewResourcesTapped: () -> Unit = {},
    onNextTapped: () -> Unit
) {
    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    val scope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        topBar =  {
            CustomToolbar("Resources")
        },
        sheetContent = {
            ContactDoctorBottomSheet(viewModel.todoItem?.doctor)
        },
        sheetShape = RoundedCornerShape(
            topStart = 20.dp,
            topEnd = 20.dp
        ),
        sheetBackgroundColor = Color.LightGray,
        sheetPeekHeight = 0.dp,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "We're sorry to hear that, would you like to contact Dr. ${viewModel.todoItem?.doctorFamilyName()} or look at some other resources online?",
                    style = MaterialTheme.typography.body1
                )
                RoundedButton(
                    modifier = Modifier
                        .topPadding(200.dp)
                        .align(Alignment.CenterHorizontally),
                    text = "Contact Doctor",
                    onTapped = {
                        scope.launch {
                            if (sheetState.isCollapsed) {
                                sheetState.expand()
                            } else {
                                sheetState.collapse()
                            }
                        }
                    }
                )
                RoundedButton(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .topPadding(20.dp),
                    text = "Resources",
                    onTapped = {
                        onViewResourcesTapped()
                    }
                )
                RoundedButton(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .topPadding(20.dp),
                    text = "No Thanks",
                    onTapped = {
                        onNextTapped()
                    }
                )
            }
        }
    )
}