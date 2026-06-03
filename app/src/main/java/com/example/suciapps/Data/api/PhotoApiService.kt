package com.example.suciapps.Data.api

import com.example.suciapps.Data.model.PhotoModel
import retrofit2.http.GET

interface PhotoApiService {
    @GET("list")
    suspend fun getPhotos(): List<PhotoModel>
}