package com.example.lacabanam.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.lacabanam.model.Reserva
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.DocumentReference


class ReservaDao {
    //firebase variables

    private var codigoUsuario: String
    private var firestore: FirebaseFirestore

    init{
        val usuario = Firebase.auth.currentUser?.email
        codigoUsuario = "$usuario"
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    //Insertar
    fun agregarReserva(reserva: Reserva){
        val document: DocumentReference

        if (reserva.id.isEmpty()){
            //proceso de agregar
            document = firestore.
            collection("LaCabana").
            document(codigoUsuario).
            collection("misReservas").
            document()
            reserva.id = document.id

        } else {
            //modificar

            document = firestore.collection("LaCabana").
            document(codigoUsuario).
            collection("misReservas").document(reserva.id)
            reserva.id = document.id
        }

        document.set(reserva).addOnCompleteListener{
            Log.d("AgregarReserva", "Guardado exitosamente")
        }
            .addOnCanceledListener { Log.e("AgregarReserva", "Error al guardar") }
    }

    //Modificar

    fun modificarReserva(reserva: Reserva){

    }

    //Delete
    fun eliminarReserva(reserva: Reserva){

        if(reserva.id.isNotEmpty()){
            firestore.collection("LaCabana").document(codigoUsuario).
            collection("misReservas").
            document(reserva.id).delete()

                .addOnCompleteListener{
                    Log.d("CancelarReserva", "Cancelado exitosamente")
                }
                .addOnCanceledListener { Log.e("CancelarReserva", "Error al cancelar") }
        }


    }

    fun ObtenerReservas(): MutableLiveData<List<Reserva>>{
        val listaReservas = MutableLiveData<List<Reserva>>()

        firestore
            .collection("LaCabana").
            document(codigoUsuario).
            collection("misReservas").addSnapshotListener { snapshot, e->
                if (e != null){
                    return@addSnapshotListener
                }
                if(snapshot != null) {
                    val lista = ArrayList<Reserva>()
                    val reservas = snapshot.documents
                    reservas.forEach {
                        val reserva = it.toObject(Reserva::class.java)
                        if (reserva != null) {
                            lista.add(reserva)
                        }
                    }
                    listaReservas.value = lista
                }
            }
        return listaReservas
    }
}
