package com.mwdev.composedemoapp.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mwdev.composedemoapp.ui.member.LandingScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(Screen.LandingScreen.route) {
            LandingScreen(navigation = navController)
        }
//        composable(
//            route = Screen.RecipeDetail.route + "/{recipeId}",
//            arguments = listOf(navArgument("recipeId") {
//                type = NavType.IntType
//            })
//        ) { navBackStackEntry ->
//            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
//            val viewModel: RecipeViewModel = viewModel("RecipeDetailViewModel", factory)
//            RecipeDetailScreen(
//                isDarkTheme = settingsDataStore.isDark.value,
//                isNetworkAvailable = connectivityManager.isNetworkAvailable.value,
//                recipeId = navBackStackEntry.arguments?.getInt("recipeId"),
//                viewModel = viewModel,
//            )
//        }
//    }
    }
}