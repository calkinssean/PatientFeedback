package com.seancalkins.patient_feedback.todo_list.domain.use_case.get_todo_item

import com.google.common.truth.Truth.assertThat
import com.seancalkins.patient_feedback.common.Resource
import com.seancalkins.patient_feedback.domain.model.*
import com.seancalkins.patient_feedback.domain.use_case.get_todo_item.GetTodoItemsUseCase
import com.seancalkins.patient_feedback.todo_list.data.repository.MockTodoItemRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetTodoItemsUseCaseTest {

    private lateinit var getTodoItemsUseCase: GetTodoItemsUseCase
    private lateinit var repository: MockTodoItemRepository

    @Before
    fun setUp() {
        repository = MockTodoItemRepository()
        getTodoItemsUseCase = GetTodoItemsUseCase(repository)
    }


    @Test
    fun `verify use case correctly converts bundle into list of todo items and emits resources correctly`() =
        runBlocking {
            val todoItems = listOf(
                TodoItem(
                    id = "0c3151bd-1cbf-4d64-b04d-cd9187a4c6e0",
                    patient = Patient(
                        givenName = "Tendo",
                        familyName = "Tenderson"
                    ),
                    doctor = Doctor(
                        familyName = "Careful"
                    ),
                    appointment = Appointment(
                        type = "Endocrinologist visit"
                    ),
                    diagnosis = Diagnosis(
                        name = "Diabetes without complications"
                    )
                )
            )

            getTodoItemsUseCase().collect {
                when (it) {
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        val items = it.data ?: emptyList()
                        assertThat(items == todoItems)
                    }
                    is Resource.Error -> {
                        assertThat(it.message == "Error with data" || it.message == "An unexpected error occurred" || it.message == "Couldn't reach server. Check your internet connection.")
                    }
                }
            }
        }


}