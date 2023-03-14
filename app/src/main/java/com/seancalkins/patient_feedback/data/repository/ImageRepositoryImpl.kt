package com.seancalkins.patient_feedback.data.repository

import android.media.Image
import com.seancalkins.patient_feedback.data.remote.ImageApi
import com.seancalkins.patient_feedback.domain.repository.ImageRepository

class ImageRepositoryImpl(
    private val api: ImageApi
): ImageRepository {

    override suspend fun uploadImage(image: Image) {
        api.uploadImage(image)
    }

}