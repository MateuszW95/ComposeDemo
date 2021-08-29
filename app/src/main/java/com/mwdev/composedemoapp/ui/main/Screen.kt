package com.mwdev.composedemoapp.ui.main

sealed class Screen(val route: String){
    object MainScreen: Screen(route = "main_screen")
    object PersonListScreen: Screen(route="person_list_screen")
    object LandingScreen: Screen(route="landing_screen")
}
