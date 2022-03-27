package com.example.samuraicase.network.service.pokeurllist

data class PokenameList(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Results>
)
