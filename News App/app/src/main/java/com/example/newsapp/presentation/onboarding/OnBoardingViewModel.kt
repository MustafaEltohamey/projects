package com.example.newsapp.presentation.onboarding


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.usecases.AppEntryUseCases
import com.example.newsapp.presentation.onboarding.componert.OnBoardingEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val useCases: AppEntryUseCases
) : ViewModel() {


    fun saveAppEntry(event: OnBoardingEvent) {
        when (event) {
            OnBoardingEvent.FinishClicked -> saveAppStateEntry()
        }
    }



    private fun saveAppStateEntry() {
        viewModelScope.launch {
            useCases.saveAppState()
        }
    }

}