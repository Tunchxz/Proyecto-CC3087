package com.example.seafriend.authentication.presentation

data class SignInState(
    val email: String = "",
    val password: String = "",
    val signInError: String? = null
)