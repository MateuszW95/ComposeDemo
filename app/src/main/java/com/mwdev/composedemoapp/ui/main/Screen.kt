package com.mwdev.composedemoapp.ui.main

sealed class Screen(val route: String){
    object MainScreen: Screen(route = "main_screen")
    object ListScreen: Screen(route="list_screen")
}
