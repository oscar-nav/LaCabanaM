package com.example.lacabanam

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.lacabanam.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase



class MainActivity : AppCompatActivity() {


    //private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var auth : FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        //Inicio de sesion
        binding.btnLogin.setOnClickListener(){ haceLogin() }

      binding.btnRegistro.setOnClickListener({ haceRegistro()})

    }


    private fun haceRegistro() {
        //Recupero la información que el usuario escribió en el App
        val email = binding.etCorreo.text.toString()
        val clave = binding.etClave.text.toString()

        Log.d("Registrándose","Haciendo llamado a creación")
        //Utilizo el objeto auth para hacer el registro...
        auth.createUserWithEmailAndPassword(email,clave)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {  //Si se logró... se creo el usuario
                    Log.d("Registrándose","se registró")
                    val user = auth.currentUser
                    refresca(user)
                } else { //Si no se logró hubo un error...
                    Log.e("Registrándose","Error de registró")
                    println(task.exception.toString())
                    Toast.makeText(baseContext,task.exception.toString(), Toast.LENGTH_LONG).show()
                    refresca(null)
                }
            }
        Log.d("Registrándose","Sale del proceso...")
    }

    private fun refresca(user: FirebaseUser?) {
        if (user != null) {  //Si hay un usuario entonce paso al pantalla principal
            val intent = Intent(this, Principal2::class.java)
            startActivity(intent)
        }
    }

    private fun haceLogin() {
        //Recupero la información que el usuario escribió en el App
        val email = binding.etCorreo.text.toString()
        val clave = binding.etClave.text.toString()

        Log.d("Autenticandose","Haciendo llamado de autenticación")
        //Utilizo el objeto auth para hacer el registro...
        auth.signInWithEmailAndPassword(email,clave)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {  //Si se logró... se creo el usuario
                    Log.d("Autenticando","se autenticó")
                    val user = auth.currentUser
                    refresca(user)
                } else { //Si no se logró hubo un error...
                    Log.e("Autenticando","Error de Autenticación")
                    println(task.exception.toString())
                    Toast.makeText(baseContext,"Fallo", Toast.LENGTH_LONG).show()
                    refresca(null)
                }
            }
        Log.d("Autenticando","Sale del proceso...")
    }

    //Esto se ejecuta toda vez que se presente el app en la pantalla, valida si hay un usuario autenticado
    public override fun onStart() {
        super.onStart()
        val usuario = auth.currentUser
        refresca(usuario)
    }
}
