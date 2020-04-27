package com.example.cardgame.data.network

import com.example.cardgame.data.network.responses.photoResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET(".")
    suspend fun getimages(
        @Query("method") method : String,
        @Query("api_key") apiKey : String,
        @Query("tags") tags : String,
        @Query("format") format : String,
        @Query("nojsoncallback") nojsoncallback : Int,
        @Query("per_page") perpage : Int
    ): Response<photoResponse>


    companion object{
        operator fun  invoke(networkConnectionInteerception: NetworkConnectionInteerception):UserService{
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInteerception)
                .build()
            return Retrofit.Builder().baseUrl("https://www.flickr.com/services/rest/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserService::class.java)
        }
    }
}