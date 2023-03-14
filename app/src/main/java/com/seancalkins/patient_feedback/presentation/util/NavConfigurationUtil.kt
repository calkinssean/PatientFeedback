package com.seancalkins.patient_feedback.presentation.util

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalUriHandler
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.seancalkins.patient_feedback.domain.model.TodoItem
import com.seancalkins.patient_feedback.presentation.Screen
import com.seancalkins.patient_feedback.presentation.feedback.FeedbackViewModel
import com.seancalkins.patient_feedback.presentation.diagnosis_feedback.DiagnosisFeedbackScreen
import com.seancalkins.patient_feedback.presentation.diagnosis_resources.DiagnosisResourcesScreen
import com.seancalkins.patient_feedback.presentation.diagnosis.DiagnosisScreen
import com.seancalkins.patient_feedback.presentation.feedback.FeedbackScreen
import com.seancalkins.patient_feedback.presentation.summary.SummaryScreen
import com.seancalkins.patient_feedback.presentation.location.EnableLocationScreen
import com.seancalkins.patient_feedback.presentation.todo_list.TodoItemsScreen

@Composable
@ExperimentalMaterialApi
fun NavConfigurationUtil(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.TodoList.route
) {
    NavHost(navController = navController, startDestination = startDestination) {
        var todoItem: TodoItem? = null
        composable(Screen.TodoList.route) {
            TodoItemsScreen {
                todoItem = it
                navController.navigate(Screen.Feedback.route)
            }
        }
        navigation(
            startDestination = Screen.Feedback.route,
            route = "submit_feedback_graph"
        ) {
            composable(Screen.Feedback.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("submit_feedback_graph")
                }
                val viewModel = hiltViewModel<FeedbackViewModel>(parentEntry)
                viewModel.addTodoItem(todoItem)
                Log.d("TestingViewModel", viewModel.todoItem?.id ?: "DIDN'T WORK")
                FeedbackScreen(viewModel) {
                    navController.navigate(Screen.Diagnosis.route)
                }
            }
            composable(Screen.Diagnosis.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("submit_feedback_graph")
                }
                val viewModel = hiltViewModel<FeedbackViewModel>(parentEntry)
                DiagnosisScreen(viewModel) {
                    if (it) {
                        navController.navigate(Screen.DiagnosisFeedback.route)
                    } else {
                        navController.navigate(Screen.DiagnosisResources.route)
                    }
                }
            }
            composable(Screen.DiagnosisResources.route) { backStackEntry ->
                val uriHandler = LocalUriHandler.current
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("submit_feedback_graph")
                }
                val viewModel = hiltViewModel<FeedbackViewModel>(parentEntry)
                DiagnosisResourcesScreen(
                    viewModel = viewModel,
                    onViewResourcesTapped = {
                        uriHandler.openUri("https://www.keanuisimmortal.com/")
                    }
                ) {
                    navController.navigate(Screen.DiagnosisFeedback.route)
                }
            }
            composable(Screen.DiagnosisFeedback.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("submit_feedback_graph")
                }
                val viewModel = hiltViewModel<FeedbackViewModel>(parentEntry)
                DiagnosisFeedbackScreen(viewModel) {
                    navController.navigate(Screen.TakePhoto.route)
                }
            }
            composable(Screen.TakePhoto.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("submit_feedback_graph")
                }
                val viewModel = hiltViewModel<FeedbackViewModel>(parentEntry)
                EnableLocationScreen(viewModel) {
                    navController.navigate(Screen.Summary.route)
                }
            }
            composable(Screen.Summary.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("submit_feedback_graph")
                }
                val viewModel = hiltViewModel<FeedbackViewModel>(parentEntry)
                SummaryScreen(viewModel) {
                    navController.navigate(Screen.TodoList.route) {
                        popUpTo(Screen.TodoList.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
}
