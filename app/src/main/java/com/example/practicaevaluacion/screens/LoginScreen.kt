package com.example.practicaevaluacion.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun LoginScreen(
    signInClicked:()->Unit
){
    Row() {
        Button(onClick = signInClicked) {
            Text(text = "Entrar")
        }
    }
}