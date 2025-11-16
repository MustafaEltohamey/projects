package com.example.newsapp.domain.usecases

import javax.inject.Inject

data class AppEntryUseCases @Inject constructor(
    val readAppState: ReadAppState,
    val saveAppState: SaveAppState
)
