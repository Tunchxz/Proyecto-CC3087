package com.example.seafriend.authentication.domain

data class User(
    val id: String = "",
    val email: String = "",
    val provider: String = "",
    val displayName: String = ""
)
