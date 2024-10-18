package com.example.seafriend.authentication.presentation

data class SignUpState(
    val email: String = "",
    val password: String = "",
    val displayName: String = "",
    val validEmail: Boolean = false,
    val validPassword: Boolean = false,
    val signUpError: String? = null
)