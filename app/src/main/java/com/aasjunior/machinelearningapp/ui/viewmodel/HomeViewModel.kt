package com.aasjunior.machinelearningapp.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.aasjunior.machinelearningapp.config.retrofit.ApiServiceImplementation
import com.aasjunior.machinelearningapp.domain.enums.AlgorithmsML
import com.aasjunior.machinelearningapp.domain.model.DataScheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.InputStreamReader

class HomeViewModel: ViewModel() {
    private val apiService = ApiServiceImplementation()

    val selectedAlgorithm = MutableStateFlow<AlgorithmsML?>(null)
    val filePath = MutableStateFlow<String?>(null)

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

    suspend fun uploadFileAndData(file: File){
        try {
            val dataScheme = DataScheme(
                src = file.path,
                attributeHeaders = _attributeHeaders.value.filterValues { it }.keys,
                classHeader = _classHeader.value ?: ""
            )

            val result = apiService.uploadFileAndData(file, dataScheme)
        }catch(e: Exception){
            Log.e("uploadFileAndDataError", e.message!!)
        }
    }
}

