package com.example.practicaevaluacion.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.practicaevaluacion.models.Ej
import com.example.practicaevaluacion.navigation.AppScreens
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddScreen(navController: NavController){
    Scaffold(
        topBar ={ TopAppBar{} },
        floatingActionButton = {
            FloatingActionButton(modifier= Modifier.size(32.dp)
                ,onClick = { navController.navigate(route= AppScreens.Principalview.route)}) {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Agregar",
                    tint = Color.White
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        BodyContent(navController)
    }
}

@Composable
fun BodyContent(navController: NavController){
    var dato by remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
        ) {
            TextField(modifier =Modifier.fillMaxWidth() ,
                value = dato,
                onValueChange ={dato=it},
                label = { Text(text = "Nombre")},
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                val nota= Ej(dato)
                Firebase.firestore.collection("notas").add(nota)
                navController.navigate(route= AppScreens.Principalview.route)
            },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Agregar alumno")
            }
        }
    }
}