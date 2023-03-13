package com.seancalkins.patient_feedback.util

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalUriHandler
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.seancalkins.patient_feedback.models.TodoItem
import com.seancalkins.patient_feedback.navigation.Screen
import com.seancalkins.patient_feedback.presentation.FeedbackViewModel
import com.seancalkins.patient_feedback.ui.diagnosis.DiagnosisFeedbackScreen
import com.seancalkins.patient_feedback.ui.diagnosis.DiagnosisResourcesScreen
import com.seancalkins.patient_feedback.ui.diagnosis.DiagnosisScreen
import com.seancalkins.patient_feedback.ui.feedback.FeedbackScreen
import com.seancalkins.patient_feedback.ui.summary.SummaryScreen
import com.seancalkins.patient_feedback.ui.todo_items.TodoItemsScreen

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
//        feedbackGraph(navController, todoItem.value)

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

private fun NavGraphBuilder.feedbackGraph(navController: NavController, todoItem: TodoItem?) {
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
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry("submit_feedback_graph")
            }
            val viewModel = hiltViewModel<FeedbackViewModel>(parentEntry)
//            DiagnosisResourcesScreen(viewModel) {
//                navController.navigate(Screen.DiagnosisFeedback.route)
//            }
        }
        composable(Screen.DiagnosisFeedback.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry("submit_feedback_graph")
            }
            val viewModel = hiltViewModel<FeedbackViewModel>(parentEntry)
            DiagnosisFeedbackScreen(viewModel) {
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

public fun NavGraphBuilder.composable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    addDestination(
        ComposeNavigator.Destination(provider[ComposeNavigator::class], content).apply {
            this.route = route
            arguments.forEach { (argumentName, argument) ->
                addArgument(argumentName, argument)
            }
            deepLinks.forEach { deepLink ->
                addDeepLink(deepLink)
            }
        }
    )
}
