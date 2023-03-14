package com.seancalkins.patient_feedback.di

import com.seancalkins.patient_feedback.data.remote.ImageApi
import com.seancalkins.patient_feedback.data.remote.TodoItemApi
import com.seancalkins.patient_feedback.data.repository.ImageRepositoryImpl
import com.seancalkins.patient_feedback.data.repository.TodoItemRepositoryImpl
import com.seancalkins.patient_feedback.domain.repository.ImageRepository
import com.seancalkins.patient_feedback.domain.repository.TodoItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoItemApi(): TodoItemApi {
        return Retrofit.Builder()
            .baseUrl("https://calkinssean.github.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TodoItemApi::class.java)
    }

    @Provides
    @Singleton
    fun provideImageApi(): ImageApi {
        return Retrofit.Builder()
            .baseUrl("https://calkinssean.github.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ImageApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTodoItemRepository(api: TodoItemApi): TodoItemRepository {
        return TodoItemRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideImageRepository(api: ImageApi): ImageRepository {
        return ImageRepositoryImpl(api)
    }



}