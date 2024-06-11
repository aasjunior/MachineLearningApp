package com.aasjunior.machinelearningapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aasjunior.machinelearningapp.config.retrofit.ApiServiceImplementation
import com.aasjunior.machinelearningapp.domain.model.KnnResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class KnnViewModel: ViewModel() {
    private val apiService = ApiServiceImplementation()

    private val _knnData = MutableStateFlow<KnnResponse?>(null)
    val knnData: StateFlow<KnnResponse?> by ::_knnData

    init {
        fetchKnnData()
    }

    private fun fetchKnnData() = viewModelScope.launch {
        try {
            val response = apiService.getKnnData()
            if(response.isSuccessful) {
                _knnData.value = response.body()
                Log.i("fetch", "${knnData.value}")
            } else {
                Log.e("fetchKnnDataError", "Erro na execução do knn: ${response.errorBody()}")
            }
        } catch (e: Exception) {
            Log.e("fetchKnnDataError", "Erro na execução do knn: ${e.message}")
        }
    }
}


