package com.seancalkins.patient_feedback.domain.repository

import android.media.Image

interface ImageRepository {

    suspend fun uploadImage(image: Image)

}