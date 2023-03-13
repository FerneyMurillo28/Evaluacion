package com.example.practicaevaluacion.navigation

sealed class AppScreens(val route:String){
  object  Principalview:AppScreens(route = "Principalview")
  object  AddScreen:AppScreens(route = "AddScreen")
}
