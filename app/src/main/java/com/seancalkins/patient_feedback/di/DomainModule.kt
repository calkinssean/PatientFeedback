package com.seancalkins.patient_feedback.di

import com.seancalkins.patient_feedback.data.repository.RepositoryImpl
import com.seancalkins.patient_feedback.domain.GetTodoItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ActivityComponent::class)
object DomainModule {

    @Provides
    fun provideGetTodoItemsUseCase(repository: RepositoryImpl): GetTodoItemsUseCase {
        return GetTodoItemsUseCase(repository)
    }

}