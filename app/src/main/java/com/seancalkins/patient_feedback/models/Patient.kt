package com.seancalkins.patient_feedback.models

data class Patient(
    val id: String? = null,
    val active: Boolean? = null,
    val names: List<Name>? = null,
    val contactMethods: List<ContactMethod>? = null,
    val gender: String? = null,
    val birthDate: String? = null,
    val addresses: List<Address>? = null
) {

    fun formattedName(): String {
        return "${givenName()} ${familyName()}"
    }

    fun givenName(): String? {
        return names?.mapNotNull { it.given }?.flatten()?.first()
    }

    fun familyName(): String? {
       return names?.firstNotNullOf { it.family }
    }

}