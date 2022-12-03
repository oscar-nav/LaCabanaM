package com.example.lacabanam.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.lacabanam.databinding.FragmentReservaFilaBinding
import com.example.lacabanam.model.Reserva
import com.example.lacabanam.ui.home.HomeFragmentDirections

class ReservaAdapter : RecyclerView.Adapter <ReservaAdapter.ReservaViewHolder>() {

    private var listaReservas = emptyList<Reserva>()

    inner class ReservaViewHolder(private val itemBinding: FragmentReservaFilaBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun dibujar(reserva: Reserva) {
            itemBinding.txtTipoHab.text = reserva.nombreHabitacion
            itemBinding.txtFechaInicio.text = reserva.fechaInicio
            itemBinding.txtFechaSalida2.text = reserva.fechaSalida
            itemBinding.txtListaAct.text = reserva.actividades
            itemBinding.txtCantidad1.text = reserva.cantidadPersonas.toString()
            itemBinding.txtCelCompleto.text = reserva.telefono


            //modificar

            itemBinding.vistaFila.setOnClickListener {
                val accion = HomeFragmentDirections
                    .actionHomeFragmentToCrearReserva()
                itemView.findNavController().navigate(accion)
            }


        }

        //evento modificar

    }

    fun setReservas(reservas: List<Reserva>) {
        listaReservas = reservas
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservaViewHolder {
        val itemBinding =
            FragmentReservaFilaBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ReservaViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: ReservaViewHolder, position: Int) {
        val reserva = listaReservas[position]
        holder.dibujar(reserva)
    }

    override fun getItemCount(): Int {
        return listaReservas.size
    }
}