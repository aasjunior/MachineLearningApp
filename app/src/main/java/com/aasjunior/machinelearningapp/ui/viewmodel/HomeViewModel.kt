package com.aasjunior.machinelearningapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.aasjunior.machinelearningapp.domain.enums.AlgorithmsML
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel: ViewModel() {
    val selectedAlgorithm = MutableStateFlow<AlgorithmsML?>(null)

    fun updateAlgorithm(algorithm: AlgorithmsML) {
        selectedAlgorithm.value = algorithm
    }
}

