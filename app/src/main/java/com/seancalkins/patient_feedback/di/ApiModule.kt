package com.seancalkins.patient_feedback.di

import com.seancalkins.patient_feedback.data.remote.TodoItemApi
import com.seancalkins.patient_feedback.data.repository.TodoItemRepositoryImpl
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
object ApiModule {

    @Provides
    @Singleton
    fun provideApi(): TodoItemApi {
        return Retrofit.Builder()
            .baseUrl("https://calkinssean.github.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TodoItemApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: TodoItemApi): TodoItemRepository {
        return TodoItemRepositoryImpl(api)
    }


}