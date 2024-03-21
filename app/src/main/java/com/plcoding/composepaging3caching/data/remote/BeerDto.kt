package com.plcoding.composepaging3caching.data.remote

data class BeerDto(
    val id: String,
    val name: String,
    val tagline: String,
    val description: String,
    val first_brewed: String,
    val image_url: String?
)
