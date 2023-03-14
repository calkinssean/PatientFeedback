package com.seancalkins.patient_feedback.presentation.feedback

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.seancalkins.patient_feedback.domain.repository.TodoItemRepository
import com.seancalkins.patient_feedback.domain.model.Feedback
import com.seancalkins.patient_feedback.data.dto.Bundle
import com.seancalkins.patient_feedback.domain.model.TodoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedbackViewModel @Inject constructor(
    private val repository: TodoItemRepository
) : ViewModel() {

    private var feedback: Feedback = Feedback()

    var submittingFeedback = mutableStateOf(false)

    var todoItem by mutableStateOf<TodoItem?>(null)
        private set

    fun addTodoItem(newTodoItem: TodoItem?) {
        todoItem = newTodoItem
    }

    fun doctorPerformance(): String = if (feedback.understoodDiagnosis) {
        "great"
    } else {
        "bad"
    }

    var doctorRecommendation: Int
        get() = feedback.doctorRecommendation
        set(newValue) {
            feedback.doctorRecommendation = newValue
        }

    var understoodDiagnosis: Boolean
        get() = feedback.understoodDiagnosis
        set(newValue) {
            feedback.understoodDiagnosis = newValue
        }

    var diagnosisFeedback: String
        get() = feedback.diagnosisFeedback
        set(newValue) {
            feedback.diagnosisFeedback = newValue
        }

    val diagnosis: String?
        get() = todoItem?.diagnosis?.name

    val doctorName: String?
        get() = todoItem?.doctor?.familyName

    val patientGivenName: String?
        get() = todoItem?.patient?.givenName

    suspend fun submitFeedback(): Boolean {
        submittingFeedback.value = true
        return try {
//            val wasSuccessful = repository.submitFeedback().body()?.success == true
            submittingFeedback.value = false
            true
//            wasSuccessful
        } catch (e: Exception) {
            submittingFeedback.value = false
            false
        }
    }

}