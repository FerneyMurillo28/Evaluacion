package com.example.practicaevaluacion

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.practicaevaluacion.navigation.AppNavigation
import com.example.practicaevaluacion.screens.LoginScreen
import com.example.practicaevaluacion.screens.Principalview
import com.example.practicaevaluacion.ui.theme.PracticaEvaluacionTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : ComponentActivity() {

    companion object{
        //Valor de inicializacion de una tarea
        const val RC_SIGN_IN=100
    }
    //VAriable nulas hasta ser modificadas
    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth=FirebaseAuth.getInstance()

        val gso= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient= GoogleSignIn.getClient(this,gso)
        setContent {
            PracticaEvaluacionTheme {
                // A surface container using the 'background' color from the theme
                if (mAuth.currentUser==null){
                    LoginScreen{
                        signIn()
                    }
                }else{
                    AppNavigation()
                    Principalview(
                        signOutClicked = {singOut()}
                    )
                }
            }
        }
    }
    private fun signIn(){
        val signInIntent=googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== RC_SIGN_IN){
            val task=GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception=task.exception
            if (task.isSuccessful){
                try{
                    val account=task.getResult(ApiException::class.java)!!
                    firebaseAuthWithGoogle(account.idToken!!)
                }catch (e:Exception){
                    Log.d("SignIn","Google sign Failed")
                }
            }else{
                Log.d("Sign In", exception.toString())
            }
        }
    }
    private fun firebaseAuthWithGoogle(idToken:String){
        val credential=GoogleAuthProvider.getCredential(idToken,null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this){task->
                if (task.isSuccessful){
                    Toast.makeText(this, "Sign In Succesgul", Toast.LENGTH_SHORT).show()
                    setContent{
                        PracticaEvaluacionTheme{
                            AppNavigation()
                            Principalview (
                                signOutClicked = {
                                    singOut()
                                }
                            )
                        }
                    }
                }else{
                    Toast.makeText(this,"Sign In", Toast.LENGTH_SHORT).show()
                }
            }
    }
    fun singOut(){
        val googleSignInClient:GoogleSignInClient
        val gso= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient=GoogleSignIn.getClient(this, gso)
        //SignInOut
        mAuth.signOut()
        googleSignInClient.signOut().addOnSuccessListener {
            Toast.makeText(this, "Sign Out Succes", Toast.LENGTH_SHORT).show()
            setContent {
                PracticaEvaluacionTheme {
                    LoginScreen {
                        signIn()
                    }
                }
            }
        }
            .addOnFailureListener{
                Toast.makeText(this,"Sign Out Failed", Toast.LENGTH_SHORT).show()
            }
    }
}

