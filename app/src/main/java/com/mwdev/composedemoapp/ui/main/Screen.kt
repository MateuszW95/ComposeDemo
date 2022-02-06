package com.mwdev.composedemoapp.ui.main

sealed class Screen(val route: String){
    object MainScreen: Screen(route = "main_screen")
    object PersonListScreen: Screen(route="person_list_screen")
    object LandingScreen: Screen(route="landing_screen")
    object ShapeScreen: Screen(route="shape_screen")
    object CalendarScreen: Screen(route="calendar_screen")
}
