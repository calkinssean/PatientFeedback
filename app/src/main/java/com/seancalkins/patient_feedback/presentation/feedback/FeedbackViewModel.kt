package com.seancalkins.patient_feedback.presentation.feedback

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seancalkins.patient_feedback.common.Resource
import com.seancalkins.patient_feedback.domain.repository.TodoItemRepository
import com.seancalkins.patient_feedback.domain.model.Feedback
import com.seancalkins.patient_feedback.data.dto.Bundle
import com.seancalkins.patient_feedback.domain.model.TodoItem
import com.seancalkins.patient_feedback.domain.use_case.submit_feedback.SubmitFeedbackUseCase
import com.seancalkins.patient_feedback.presentation.todo_list.TodoItemListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FeedbackViewModel @Inject constructor(
    private val submitFeedbackUseCase: SubmitFeedbackUseCase
) : ViewModel() {

    private val _state = mutableStateOf(SubmitFeedbackState())
    val state: State<SubmitFeedbackState> = _state

    private var feedback = Feedback()

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

    fun submitFeedback(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        submitFeedbackUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = SubmitFeedbackState(false)
                    onSuccess()
                }
                is Resource.Error -> {
                    _state.value = SubmitFeedbackState(false)
                    onError(result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = SubmitFeedbackState(true)
                }
            }
        }.launchIn(viewModelScope)
    }

}