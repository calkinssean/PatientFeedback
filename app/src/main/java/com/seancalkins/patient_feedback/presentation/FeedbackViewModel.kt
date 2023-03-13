package com.seancalkins.patient_feedback.presentation

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.seancalkins.patient_feedback.domain.repository.Repository
import com.seancalkins.patient_feedback.models.Doctor
import com.seancalkins.patient_feedback.models.Feedback
import com.seancalkins.patient_feedback.models.TodoItem
import com.seancalkins.patient_feedback.models.Patient
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedbackViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var submittingFeedback = mutableStateOf(false)

    var todoItem by mutableStateOf<TodoItem?>(null)
        private set

    fun addTodoItem(newTodoItem: TodoItem?) {
        todoItem = newTodoItem
    }

    val feedback = Feedback()

    fun doctorPerformance(): String = if (feedback.understoodDiagnosis) {
        "great"
    } else {
        "bad"
    }

    suspend fun submitFeedback(): Boolean {
        submittingFeedback.value = true
        val wasSuccessful = repository.submitFeedback(feedback).isSuccessful
        submittingFeedback.value = false
        return wasSuccessful
    }

}