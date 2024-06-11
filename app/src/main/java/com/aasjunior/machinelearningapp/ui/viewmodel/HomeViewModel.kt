package com.aasjunior.machinelearningapp.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
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

    private val selectedFile = MutableStateFlow<File?>(null)

    private val _attributeHeaders = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    val attributeHeaders: StateFlow<Map<String, Boolean>> = _attributeHeaders

    private val _classHeader = MutableStateFlow<String?>(null)
    val classHeader: StateFlow<String?> = _classHeader

    val useIrisData = mutableStateOf(false)

    suspend fun readCSV(file: File) = withContext(Dispatchers.IO){
        val reader = BufferedReader(FileReader(file))
        val headerLine = reader.readLine()

        _attributeHeaders.value = headerLine
            .split(",")
            .associateWith { false }

        selectedFile.value = file
    }

    suspend fun readLocalResCSV(context: Context, resourceId: Int) = withContext(Dispatchers.IO){
        val inputStream = context.resources.openRawResource(resourceId)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val headerLine = reader.readLine()

        _attributeHeaders.value = headerLine
            .split(",")
            .associateWith { false }

        val tempFile = File.createTempFile("temp", null, context.cacheDir)
        tempFile.deleteOnExit()

        inputStream.use { input ->
            tempFile.outputStream().use { fileOut ->
                input.copyTo(fileOut)
            }
        }

        selectedFile.value = tempFile
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

    suspend fun uploadFileAndData(){
        try {
            val file = selectedFile.value
            if (file != null) {
                val dataScheme = DataScheme(
                    src = file.path,
                    attributeHeaders = _attributeHeaders.value.filterValues { it }.keys,
                    classHeader = _classHeader.value ?: ""
                )

                val result = apiService.uploadFileAndData(file, dataScheme)
                Log.i("uploadFileAndData", "${result}")
            } else {
                Log.e("uploadFileAndDataError", "Nenhum arquivo selecionado")
            }
        }catch(e: Exception){
            Log.e("uploadFileAndDataError", e.message!!)
        }
    }
}

