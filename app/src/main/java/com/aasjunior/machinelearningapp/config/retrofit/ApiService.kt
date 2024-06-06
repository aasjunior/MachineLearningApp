package com.aasjunior.machinelearningapp.config.retrofit

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
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

    @Multipart
    @POST("/decision-tree")
    suspend fun decisionTree(
        @Part file: MultipartBody.Part,
        @PartMap dataScheme: Map<String, @JvmSuppressWildcards RequestBody>
    ): ResponseBody

    @GET("/genetic-algorithm")
    suspend fun geneticAlgorithm(): ResponseBody

    @GET("/")
    suspend fun gettingStarted(): ResponseBody

}