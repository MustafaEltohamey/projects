package com.example.newsapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.usecases.AppEntryUseCases
import com.example.newsapp.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
): ViewModel(){

    private val _SplashCondition = mutableStateOf(true)
    val splashCondition : State<Boolean> = _SplashCondition

    private val _StartDestination = mutableStateOf(Route.AppStartNavigation.route)
    val startDestination : State<String> = _StartDestination

    init {
        appEntryUseCases.readAppState().onEach { appStartState ->

            if (appStartState) {
                _StartDestination.value = Route.NewsNavigation.route
            } else {
                _StartDestination.value = Route.AppStartNavigation.route
            }
            delay(200)
            _SplashCondition.value = false

        }.launchIn(viewModelScope)
    }

}
