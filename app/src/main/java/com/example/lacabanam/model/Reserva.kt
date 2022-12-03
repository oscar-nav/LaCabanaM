package com.example.lacabanam.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class Reserva (

    var id: String,
    val nombreHabitacion: String?,
    var fechaInicio: String?,
    val fechaSalida: String?,
    val actividades: String?,
    val cantidadPersonas: Int,
    val telefono: String?

) : Parcelable {
    constructor():
            this("", "", "", "", "", 0, "")
}
