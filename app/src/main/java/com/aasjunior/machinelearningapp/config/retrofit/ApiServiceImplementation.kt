package com.aasjunior.machinelearningapp.config.retrofit

import com.aasjunior.machinelearningapp.domain.model.DataScheme
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import java.io.File

class ApiServiceImplementation: ApiService {
    override suspend fun knn(
        file: MultipartBody.Part,
        dataScheme: Map<String, RequestBody>
    ): ResponseBody {
        return RetrofitService.apiService.knn(file, dataScheme)
    }

    override suspend fun decisionTree(
        file: MultipartBody.Part,
        dataScheme: Map<String, RequestBody>
    ): ResponseBody {
        return RetrofitService.apiService.decisionTree(file, dataScheme)
    }

    override suspend fun geneticAlgorithm(): ResponseBody {
        return RetrofitService.apiService.geneticAlgorithm()
    }

    override suspend fun gettingStarted(): ResponseBody {
        return RetrofitService.apiService.gettingStarted()
    }

    suspend fun uploadFileAndData(file: File, dataScheme: DataScheme): String{
        val requestFile = file.asRequestBody("text/csv".toMediaType())
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

        val dataSchemeMap = mapOf(
            "src" to dataScheme.src
                .toRequestBody("text/plain".toMediaType()),

            "attributeHeaders" to dataScheme.attributeHeaders
                .joinToString()
                .toRequestBody("text/plain".toMediaType()),

            "classHeader" to dataScheme.classHeader
                .toRequestBody("text/plain".toMediaType())
        )

        return try {
            val response = this.knn(body, dataSchemeMap)
            "File and data uploaded successfully: ${response.string()}"
        } catch (e: Exception) {
            "File and data upload failed: ${e.message}"
        }
    }
}