package com.example.seafriend.authentication.domain

import kotlinx.coroutines.flow.Flow

interface AccountService {
    val currentUser: Flow<User?>
    val currentUserId: String
    fun hasUser(): Boolean
    fun getUserProfile(): User
    suspend fun updateDisplayName(newDisplayName: String)
    suspend fun linkAccount(email: String, password: String)
    suspend fun signUp(email: String,password: String)
    suspend fun signIn(email: String, password: String)
    suspend fun signOut()
    suspend fun deleteAccount()
}