package com.example.newsapp.domain.usecases

import com.example.newsapp.domain.manager.DataStoreManager
import javax.inject.Inject

class SaveAppState @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {

    suspend operator fun invoke() {
        dataStoreManager.saveAppEntry()
    }
}