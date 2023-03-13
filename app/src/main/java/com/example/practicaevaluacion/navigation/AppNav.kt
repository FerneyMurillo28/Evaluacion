package com.example.practicaevaluacion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practicaevaluacion.models.EjViewModel
import com.example.practicaevaluacion.screens.AddScreen
import com.example.practicaevaluacion.screens.AppAlumnos

@Composable
fun AppNavigation(){
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.Principalview.route){
        composable(route = AppScreens.Principalview.route){ AppAlumnos(navController, EjViewModel())}
        composable(route = AppScreens.AddScreen.route){ AddScreen(navController)}
    }
}