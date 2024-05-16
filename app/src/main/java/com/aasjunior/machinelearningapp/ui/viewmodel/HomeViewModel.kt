package com.aasjunior.machinelearningapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel: ViewModel() {
    private val _selectedOptionText = MutableStateFlow<String>("")
    val selectedOptionText: StateFlow<String> get() = _selectedOptionText

    fun setSelectedOptionText(value: String) {
        _selectedOptionText.value = value
    }
}