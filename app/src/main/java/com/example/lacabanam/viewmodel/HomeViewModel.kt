package com.example.lacabanam.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lacabanam.data.ReservaDao
import com.example.lacabanam.model.Reserva
import com.example.lacabanam.repository.ReservaRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    val obtenerReservas : MutableLiveData<List<Reserva>>
    private val repository: ReservaRepository

    init {
        repository = ReservaRepository(ReservaDao())
        obtenerReservas = repository.obtenerReserva
    }

    fun agregarReserva(reserva: Reserva) {
        repository.agregarReserva(reserva)
    }

    fun eliminarReserva(reserva: Reserva) {
        repository.eliminarReserva(reserva)
    }


}