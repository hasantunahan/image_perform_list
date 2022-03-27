package com.example.samuraicase.network.service

import com.example.samuraicase.network.constant.ApplicationConstant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        if (retrofit == null)
            retrofit =
                Retrofit.Builder().baseUrl(ApplicationConstant.baseUrl)
                    .addConverterFactory(
                    GsonConverterFactory.create()
                ).build()

        return retrofit as Retrofit
    }
}