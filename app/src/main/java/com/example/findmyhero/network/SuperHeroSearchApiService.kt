package com.example.findmyhero.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://superhero-search.p.rapidapi.com"
val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(logging)
    .addInterceptor { chain ->
        val newRequest = chain.request().newBuilder()
            .addHeader("X-RapidAPI-Key", "ce9eab9b4bmsh0663cc0f5f99061p12ecf0jsn3b2d6ef29cf1")
            .addHeader("X-RapidAPI-Host", "superhero-search.p.rapidapi.com")
            .build()
        chain.proceed(newRequest)
    }
    .build()

val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit: Retrofit = Retrofit.Builder()
    .client(client)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

interface SuperHeroSearchApiService {
    @GET("api/heroes")
    fun randomTwentySuperHeroAsync(): Deferred<List<Superhero>>

    @GET("api/")
    fun getSearchedSuperHeroAsync(@Query("hero") hero: String) : Deferred<Superhero>
}

object SuperHeroAPI{
    val superHeroApiService: SuperHeroSearchApiService by lazy {
        retrofit.create(SuperHeroSearchApiService::class.java)
    }
}
