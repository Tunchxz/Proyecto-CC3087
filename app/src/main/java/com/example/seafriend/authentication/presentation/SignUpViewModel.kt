package com.example.seafriend.authentication.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.seafriend.SeaFriendViewModel
import com.example.seafriend.authentication.domain.AccountService

class SignUpViewModel(
    private val accountService: AccountService
) : SeaFriendViewModel() {

    // Delegated property for sign-up state
    var signUpState by mutableStateOf(SignUpState())
        private set

    fun updateEmail(newEmail: String) {
        signUpState = signUpState.copy(
            email = newEmail,
            validEmail = isValidEmail(newEmail)
        )
    }

    fun updatePassword(newPassword: String) {
        signUpState = signUpState.copy(
            password = newPassword,
            validPassword = newPassword.length >= 8 // More idiomatic to use 'length'
        )
    }

    fun updateDisplayName(displayName: String) {
        signUpState = signUpState.copy(
            displayName = displayName
        )
    }

    fun onSignUpClick() {
        if (signUpState.validEmail && signUpState.validPassword) {
            launchCatching {
                try {
                    // Attempt to sign up using the accountService
                    accountService.signUp(signUpState.email, signUpState.password)
                    accountService.updateDisplayName(signUpState.displayName)
                } catch (e: Exception) {
                    signUpState = signUpState.copy(
                        signUpError = "No se pudo crear el usuario"
                    )
                }
            }
        }
    }

    fun isSignUpDone(): Boolean {
        return accountService.hasUser()
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return email.matches(emailRegex.toRegex())
    }
}