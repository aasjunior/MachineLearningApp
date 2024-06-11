package com.aasjunior.machinelearningapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.aasjunior.machinelearningapp.config.retrofit.ApiServiceImplementation

class KnnViewModel: ViewModel() {
    private val apiService = ApiServiceImplementation()

}