package com.example.samuraicase.network.service.pokeurllist

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IPokemonService {

    @GET("pokemon/{id}")
    fun getPokemonById(
        @Path("id") id: Int,
    ): Single<Pokemon>

}