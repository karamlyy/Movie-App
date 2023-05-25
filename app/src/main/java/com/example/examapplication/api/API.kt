package com.example.examapplication.api


import com.example.examapplication.Constants
import com.example.examapplication.model.Result
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query

fun buildAPI(): API{
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(API::class.java)
}

interface API {

    companion object{
        val instance: API = buildAPI()
    }

    @GET("search/movie")
    fun searchMovie(@Query("api_key")api_key : String, @Query("query")query : String) : Call<Result?>?

    @GET("movie/upcoming")
    fun getUpcomingMovie(@Query("api_key")apiKey : String) : Call<Result?>?

    @GET("movie/popular")
    fun getPopularMovie(@Query("api_key")apiKey : String) : Call<Result?>?
}