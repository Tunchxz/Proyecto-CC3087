package com.example.seafriend

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.seafriend.navigation.AppNavGraph

@Composable
fun SeaFriendApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    AppNavGraph(
        navHostController = navController,
        modifier = modifier
    )
}