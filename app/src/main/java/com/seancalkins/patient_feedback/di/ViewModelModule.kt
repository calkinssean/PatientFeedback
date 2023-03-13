package com.seancalkins.patient_feedback.di

import android.util.Log
import com.seancalkins.patient_feedback.domain.repository.Repository
import com.seancalkins.patient_feedback.models.Feedback
import com.seancalkins.patient_feedback.presentation.FeedbackViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(ViewModelComponent::class)
//class ViewModelModule {
//
//    @Provides
//    @ViewModelScoped
//    fun provideFeedbackViewModel(repository: Repository): FeedbackViewModel {
//        Log.d("FeedbackViewModelDebug", "Creating a new instance of FeedbackViewModel")
//        return FeedbackViewModel(repository)
//    }
//
//}