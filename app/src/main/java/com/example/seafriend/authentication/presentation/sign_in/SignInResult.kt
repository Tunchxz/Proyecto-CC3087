package com.example.seafriend.authentication.presentation.sign_in

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)