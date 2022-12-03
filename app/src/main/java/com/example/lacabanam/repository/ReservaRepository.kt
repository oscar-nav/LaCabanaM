package com.example.lacabanam.repository

import androidx.lifecycle.MutableLiveData
import com.example.lacabanam.data.ReservaDao
import com.example.lacabanam.model.Reserva


class ReservaRepository(private val reservaDao: ReservaDao) {

    fun agregarReserva(reserva: Reserva) {
        reservaDao.agregarReserva(reserva)
    }

    fun eliminarReserva(reserva: Reserva) {
        reservaDao.eliminarReserva(reserva)
    }

    val obtenerReserva: MutableLiveData<List<Reserva>> = reservaDao.ObtenerReservas()
}
