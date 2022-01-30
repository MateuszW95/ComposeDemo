package com.mwdev.composedemoapp.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mwdev.composedemoapp.ui.member.LandingScreen
import com.mwdev.composedemoapp.ui.person_list.PersonListScreen
import com.mwdev.composedemoapp.ui.person_list.PersonViewModel
import com.mwdev.composedemoapp.ui.shapes.ShapeScreen
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory

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
        composable(route = Screen.PersonListScreen.route) {
            val viewModel: PersonViewModel= hiltViewModel()
            PersonListScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screen.ShapeScreen.route) {
            ShapeScreen(navController = navController)
        }
    }
}