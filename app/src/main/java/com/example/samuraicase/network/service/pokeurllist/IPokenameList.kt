package com.example.samuraicase.network.service.pokeurllist

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IPokenameList {

    @GET("pokemon")
    fun getPokenameList(
        @Query("limit") limit: Int
    ): Call<PokenameList>

    @GET("pokemon/{id}")
    fun getPokemonById(
        @Path("id") id: Int
    ): Call<Pokemon>

}