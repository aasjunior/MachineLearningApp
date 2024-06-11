package com.aasjunior.machinelearningapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aasjunior.machinelearningapp.config.retrofit.ApiServiceImplementation
import com.aasjunior.machinelearningapp.domain.model.DecisionTreeResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DecisionTreeViewModel: ViewModel() {
    private val apiService = ApiServiceImplementation()

    private val _decisionTreeData = MutableStateFlow<DecisionTreeResponse?>(null)
    val decisionTreeData: StateFlow<DecisionTreeResponse?> by ::_decisionTreeData

    init {
        fetchDecisionTreeData()
    }

    private fun fetchDecisionTreeData() = viewModelScope.launch {
        try {
            val response = apiService.getDecisionTreeData()
            if(response.isSuccessful) {
                _decisionTreeData.value = response.body()
                Log.i("fetch", "${_decisionTreeData.value}")
            } else {
                Log.e("fetchDecisionTreeDataError", "Erro na execução do algoritmo de arvore de decisão: ${response.errorBody()}")
            }
        } catch (e: Exception) {
            Log.e("fetchDecisionTreeDataError", "Erro na execução do algoritmo de arvore de decisão: ${e.message}")
        }
    }
}