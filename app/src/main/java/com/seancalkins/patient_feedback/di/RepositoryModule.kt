package com.seancalkins.patient_feedback.di

import com.seancalkins.patient_feedback.data.remote.Api
import com.seancalkins.patient_feedback.data.repository.RepositoryImpl
import com.seancalkins.patient_feedback.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(api: Api): Repository {
        return RepositoryImpl(api)
    }

}