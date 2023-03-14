package com.seancalkins.patient_feedback.domain.use_case.take_photo

import android.media.Image
import com.seancalkins.patient_feedback.common.Resource
import com.seancalkins.patient_feedback.domain.repository.ImageRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UploadPhotoUseCase @Inject constructor(
    private val repository: ImageRepository
) {

    operator fun invoke(image: Image): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading<Boolean>())
            delay(500L)
            val response = repository.uploadImage(image)
            emit(Resource.Success<Boolean>(true))
        } catch (e: HttpException) {
            emit(Resource.Error<Boolean>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<Boolean>("Couldn't reach server. Check your internet connection."))
        }
    }

}