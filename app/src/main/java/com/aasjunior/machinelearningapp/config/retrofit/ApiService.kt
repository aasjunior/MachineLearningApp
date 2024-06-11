package com.aasjunior.machinelearningapp.config.retrofit

import com.aasjunior.machinelearningapp.domain.model.DataScheme
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
    suspend fun knn(
        @Part file: MultipartBody.Part,
        @PartMap dataScheme: Map<String, @JvmSuppressWildcards RequestBody>
    ): ResponseBody

    @POST("/knn")
    @Multipart
    suspend fun knnPost(
        @Part("data") data: DataScheme,
        @Part file: MultipartBody.Part
    ): KnnResponse

    @GET("/genetic-algorithm")
    suspend fun getGeneticAlgorithmData(): Response<GeneticAlgorithmResponse>
}

data class KnnResponse(val result: Map<String, Any>)
