package com.example.practicaevaluacion.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practicaevaluacion.R
import com.example.practicaevaluacion.models.Ej
import com.example.practicaevaluacion.models.EjViewModel
import com.example.practicaevaluacion.navigation.AppScreens
import com.example.practicaevaluacion.ui.theme.PracticaEvaluacionTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

@Composable
fun Principalview(
    signOutClicked: () -> Unit
){
    Button(onClick = signOutClicked) {
        Text(text = "Salir")
    }
}


@Composable
fun AlumnoCard(notas:Ej){
    Card(modifier = Modifier
        .padding(all = 16.dp)
        .fillMaxWidth()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Nombre: ${notas.dato}",color= Color.DarkGray, fontSize = 16.sp)
        }//fincolum
    }//fincard
}//funFunAlumnos

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppAlumnos(navController: NavController, viewModel: EjViewModel){
    Scaffold(
        topBar={
            TopAppBar {}
        },
        floatingActionButton = {
            FloatingActionButton(modifier = Modifier.size(32.dp),
                onClick = {navController.navigate(route = AppScreens.AddScreen.route)}) {
                Icon(imageVector = Icons.Default.AddCircle,
                    contentDescription ="Agregar",
                    tint = Color.White
                )//FinIcon
            }//FinFloatingbutton
        },//FinFloatingActionButton
        floatingActionButtonPosition = FabPosition.End
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            Column() {
                LazyColumn(){
                    items(viewModel.notas.value){nota->
                        AlumnoCard(nota)
                    }
                }
            }//fincolum
        }//finbox
    }
}//FinFunAppAlumnos
