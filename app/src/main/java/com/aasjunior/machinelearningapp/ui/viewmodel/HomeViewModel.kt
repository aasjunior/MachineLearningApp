package com.aasjunior.machinelearningapp.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.aasjunior.machinelearningapp.domain.enums.AlgorithmsML
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.InputStreamReader

class HomeViewModel: ViewModel() {
    val selectedAlgorithm = MutableStateFlow<AlgorithmsML?>(null)

    private val _attributeHeaders = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    val attributeHeaders: StateFlow<Map<String, Boolean>> = _attributeHeaders

    private val _classHeader = MutableStateFlow<String?>(null)
    val classHeader: StateFlow<String?> = _classHeader

    suspend fun readCSV(file: File) = withContext(Dispatchers.IO){
        val reader = BufferedReader(FileReader(file))
        val headerLine = reader.readLine()

        _attributeHeaders.value = headerLine
            .split(",")
            .associateWith { false }
    }

    suspend fun readLocalResCSV(context: Context, resourceId: Int) = withContext(Dispatchers.IO){
        val inputStream = context.resources.openRawResource(resourceId)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val headerLine = reader.readLine()

        _attributeHeaders.value = headerLine
            .split(",")
            .associateWith { false }
    }

    fun updateAttributeSelection(header: String, isSelected: Boolean){
        val currentHeaders = _attributeHeaders.value
        _attributeHeaders.value = currentHeaders + (header to isSelected)
    }

    fun updateClassSelection(header: String){
        _classHeader.value = header
    }

    fun updateAlgorithm(algorithm: AlgorithmsML) {
        selectedAlgorithm.value = algorithm
    }
}

