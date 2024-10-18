package com.example.seafriend.authentication.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.seafriend.SeaFriendViewModel
import com.example.seafriend.authentication.domain.AccountService
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class SignInViewModel(
    private val accountService: AccountService
) : SeaFriendViewModel() {
    // Delegated property for sign-in state
    var signInState by mutableStateOf(SignInState())
        private set

    fun updateEmail(newEmail: String) {
        signInState = signInState.copy(
            email = newEmail
        )
    }

    fun updatePassword(newPassword: String) {
        signInState = signInState.copy(
            password = newPassword
        )
    }

    fun isSignInDone(): Boolean {
        return accountService.hasUser()
    }

    fun onSignInClick() {
        launchCatching {
            try {
                // Attempt to sign in using the accountService
                accountService.signIn(signInState.email, signInState.password)
            } catch (e: Exception) {
                signInState = signInState.copy(
                    signInError = when (e) {
                        is FirebaseAuthInvalidUserException -> "User not found: ${e.message}"
                        is FirebaseAuthInvalidCredentialsException -> "Invalid credentials: ${e.message}"
                        is FirebaseNetworkException -> "Network error: ${e.message}"
                        else -> "Unknown error: ${e.message}"
                    }
                )
            }
        }
    }
}