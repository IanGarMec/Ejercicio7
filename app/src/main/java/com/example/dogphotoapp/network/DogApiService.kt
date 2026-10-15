package com.example.dogphotoapp.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.example.dogphotoapp.model.DogResponse
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL = "https://dog.ceo/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface DogApiService {
    @GET("api/breeds/image/random/10")
    suspend fun getPhotos(): DogResponse
}

object DogApi {
    val retrofitService: DogApiService by lazy {
        retrofit.create(DogApiService::class.java)
    }
}