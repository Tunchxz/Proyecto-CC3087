package com.example.seafriend.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.seafriend.authentication.presentation.SignInScreen
import com.example.seafriend.authentication.presentation.SignUpScreen
import com.example.seafriend.mainapp.presentation.HomeScreen

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = SIGN_IN_SCREEN,
        modifier = modifier
    ) {
        composable(route = SIGN_IN_SCREEN) {
            SignInScreen(
                onSignInClick = {
                    navHostController.navigate(HOME_SCREEN) {
                        launchSingleTop = true
                        popUpTo(SIGN_IN_SCREEN) { inclusive = true }
                    }
                },
                onSignUpClick = {
                    navHostController.navigate(SIGN_UP_SCREEN) {
                        launchSingleTop = true
                        popUpTo(SIGN_IN_SCREEN) { inclusive = true }
                    }
                }
            )
        }

        composable(route = SIGN_UP_SCREEN) {
            SignUpScreen(
                onSignUpClick = {
                    navHostController.navigate(HOME_SCREEN) {
                        launchSingleTop = true
                        popUpTo(SIGN_UP_SCREEN) { inclusive = true }
                    }
                },
                onSignInClick = {
                    navHostController.navigate(SIGN_IN_SCREEN) {
                        launchSingleTop = true
                        popUpTo(SIGN_UP_SCREEN) { inclusive = true }
                    }
                }
            )
        }

        composable(route = HOME_SCREEN) {
            HomeScreen()
        }
    }
}