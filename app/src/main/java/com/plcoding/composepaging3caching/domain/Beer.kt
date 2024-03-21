package com.plcoding.composepaging3caching.domain

data class Beer(
    val id: String,
    val name: String,
    val tagline: String,
    val description: String,
    val firstBrewed: String,
    val imageUrl: String?) {
}