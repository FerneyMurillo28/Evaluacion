package com.example.practicaevaluacion.models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

class EjViewModel :ViewModel(){
    private val _notas= mutableStateOf<List<Ej>>(emptyList())
    val notas: State<List<Ej>>
        get()=_notas
    private val query=Firebase.firestore.collection("notas")
    init{
        query.addSnapshotListener{value,_->
            if (value!=null){
                _notas.value=value.toObjects()
            }
        }
    }
}