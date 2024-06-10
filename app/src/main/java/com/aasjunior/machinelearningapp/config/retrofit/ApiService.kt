package com.aasjunior.machinelearningapp.config.retrofit

import com.aasjunior.machinelearningapp.domain.model.GeneticAlgorithmResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap

interface ApiService {
    @Multipart
    @POST("/knn")
    suspend fun uploadFileWithPartMap(
        @Part file: MultipartBody.Part,
        @PartMap dataScheme: Map<String, @JvmSuppressWildcards RequestBody>
    ): ResponseBody

    @GET("/genetic-algorithm")
    suspend fun getGeneticAlgorithmData(): Response<GeneticAlgorithmResponse>
}