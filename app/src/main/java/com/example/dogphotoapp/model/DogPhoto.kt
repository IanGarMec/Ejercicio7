package com.example.dogphotoapp.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class DogPhoto(
    val id: String,
    val url: String
)

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class DogResponse(
    val message: List<String>,
    val status: String
)