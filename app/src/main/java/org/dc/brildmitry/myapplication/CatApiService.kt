package org.dc.brildmitry.myapplication

import retrofit2.http.GET
import retrofit2.http.Query

data class CatImageResponse(
    val id: String,
    val url: String,
    val width: Int,
    val height: Int,
    val breeds: List<Breed> = emptyList()
)

data class Breed(
    val id: String,
    val name: String,
    val temperament: String
)

interface CatApiService {
    @GET("v1/images/search")
    suspend fun getCatImages(@Query("limit") limit: Int = 10): List<CatImageResponse>
}
