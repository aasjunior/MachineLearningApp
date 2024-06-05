package com.aasjunior.machinelearningapp.config.retrofit

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import java.io.File

interface ApiService {
    @Multipart
    @POST("/knn")
    suspend fun uploadCSV(@Part file: MultipartBody.Part): Response<Unit>
}

suspend fun uploadFile(file: File): String{
    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8000")
        .build()

    val service = retrofit.create(ApiService::class.java)

    val requestFile = file
        .asRequestBody("text/csv".toMediaTypeOrNull())

    val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

    val response = service.uploadCSV(body)

    return if(response.isSuccessful){
        "Upload succesful"
    }else{
        "Upload failed: ${response.errorBody()?.string()}"
    }
}