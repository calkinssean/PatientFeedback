package com.seancalkins.patient_feedback.data.repository

import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.seancalkins.patient_feedback.data.dto.*
import com.seancalkins.patient_feedback.data.remote.TodoItemApi
import com.seancalkins.patient_feedback.domain.repository.TodoItemRepository
import com.seancalkins.patient_feedback.todo_list.data.remote.JsonLoader
import com.seancalkins.patient_feedback.todo_list.data.repository.MockTodoItemRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
class TodoItemRepositoryImplTest {

    private lateinit var repository: TodoItemRepository
    private lateinit var api: TodoItemApi
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        api = Retrofit.Builder()
            .baseUrl("/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TodoItemApi::class.java)
        repository = MockTodoItemRepository()
    }

    @Test
    fun `on success, api must return bundle with code 200`() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val bundle = getBundle()
        val expectedResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(JsonLoader.loadJsonIntoMemory(context, "patient_feedback_raw_data.json"))
        mockWebServer.enqueue(expectedResponse)

        val actualResponse = repository.getTodoItem()
        assertThat(actualResponse == bundle)
        Unit
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    private fun getBundle(): Bundle {
        return Bundle(
            resourceType = "Bundle",
            id = "0c3151bd-1cbf-4d64-b04d-cd9187a4c6e0",
            timestamp = "2021-04-02T12:12:21Z",
            entry = listOf(
                ResourceWrapper(
                    Resource(
                        resourceType = "Patient",
                        id = "6739ec3e-93bd-11eb-a8b3-0242ac130003",
                        active = true,
                        name = listOf(
                            Name(
                                text = "Tendo Tenderson",
                                family = "Tenderson",
                                given = listOf(
                                    "Tendo"
                                )
                            )
                        ),
                        contact = listOf(
                            ContactMethod(
                                system = "phone",
                                value = "555-555-2021",
                                use = "mobile"
                            ),
                            ContactMethod(
                                system = "email",
                                value = "tendo@tendoco.com",
                                use = "work"
                            )
                        ),
                        gender = "female",
                        birthDate = "1955-01-06",
                        address = listOf(
                            Address(
                                use = "home",
                                line = listOf(
                                    "2222 Home Street"
                                )
                            )
                        )
                    ),
                ),
                ResourceWrapper(
                    Resource(
                        resourceType = "Doctor",
                        id = "9bf9e532-93bd-11eb-a8b3-0242ac130003",
                        name = listOf(
                            Name(
                                family = "Careful",
                                given = listOf("Adam")
                            )
                        )
                    )
                ),
                ResourceWrapper(
                    Resource(
                        resourceType = "Appointment",
                        id = "be142dc6-93bd-11eb-a8b3-0242ac130003",
                        status = "finished",
                        type = listOf(
                            AppointmentType(
                                text = "Endocrinologist visit"
                            )
                        ),
                        subject = ReferenceItem(
                            reference = "Patient /6739ec3e-93bd-11eb-a8b3-0242ac130003"
                        ),
                        actor = ReferenceItem(
                            reference = "Doctor/9bf9e532-93bd-11eb-a8b3-0242ac130003"
                        ),
                        period = Period(
                            start = "2021-04-02T11:30:00Z",
                            end = "2021-04-02T12:00:00Z"
                        )
                    )
                ),
                ResourceWrapper(
                    Resource(
                        resourceType = "Diagnosis",
                        id = "541a72a8-df75-4484-ac89-ac4923f03b81",
                        meta = MetaData(
                            lastUpdated = "2021-04-02T11:51:03Z"
                        ),
                        status = "final",
                        code = Code(
                            coding = listOf(
                                CodingItem(
                                    system = "http://hl7.org/fhir/sid/icd-10",
                                    code = "E10-E14.9",
                                    name = "Diabetes without complications"
                                )
                            )
                        ),
                        appointment = ReferenceItem(
                            reference = " Appointment/be142dc6-93bd-11eb-a8b3-0242ac130003"
                        )
                    )
                )
            )
        )
    }

}
