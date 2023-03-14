package com.seancalkins.patient_feedback.domain.model

import com.seancalkins.patient_feedback.data.dto.Address
import com.seancalkins.patient_feedback.data.dto.ContactMethod
import com.seancalkins.patient_feedback.data.dto.Name

data class Patient(
    val givenName: String,
    val familyName: String
)