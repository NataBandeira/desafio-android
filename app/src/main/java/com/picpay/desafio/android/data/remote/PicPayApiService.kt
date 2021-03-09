package com.picpay.desafio.android.data.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PicPayApiService{

    @GET("users")
    fun getUsers(): Call<List<UserProperty>>

    companion object {
        private const val BASE_URL = "http://careers.picpay.com/tests/mobdev/"

        fun create(): PicPayApiService {
            val gson: Gson by lazy { GsonBuilder().create() }

            val okHttp: OkHttpClient by lazy {
                OkHttpClient.Builder()
                    .build()
            }

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(PicPayApiService::class.java)
        }
    }
}