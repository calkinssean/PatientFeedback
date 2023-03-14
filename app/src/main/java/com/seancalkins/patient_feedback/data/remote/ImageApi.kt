package com.seancalkins.patient_feedback.data.remote

import android.media.Image
import com.seancalkins.patient_feedback.data.dto.Bundle
import retrofit2.http.GET
import retrofit2.http.POST

interface ImageApi {

    @POST("image/upload/")
    suspend fun uploadImage(image: Image): Bundle

}