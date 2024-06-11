package com.aasjunior.machinelearningapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aasjunior.machinelearningapp.config.retrofit.ApiServiceImplementation
import com.aasjunior.machinelearningapp.domain.model.GeneticAlgorithmResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GeneticAlgorithmViewModel: ViewModel() {
    private val apiService = ApiServiceImplementation()

    private val _geneticAlgorithmData = MutableStateFlow<GeneticAlgorithmResponse?>(null)
    val geneticAlgorithmData: StateFlow<GeneticAlgorithmResponse?> by ::_geneticAlgorithmData

    init {
        fetchGeneticAlgorithmData()
    }

    fun fetchGeneticAlgorithmData() = viewModelScope.launch {
        try {
            val response = apiService.getGeneticAlgorithmData()
            if (response.isSuccessful) {
                _geneticAlgorithmData.value = response.body()
                Log.i("fetch", "${geneticAlgorithmData.value}")
            } else {
                Log.e("fetchGeneticAlgorithmDataError", "Erro ao buscar dados do algoritmo genético: ${response.errorBody()}")
            }
        } catch (e: Exception) {
            Log.e("fetchGeneticAlgorithmDataError", "Erro ao buscar dados do algoritmo genético: ${e.message}")
        }
    }
}