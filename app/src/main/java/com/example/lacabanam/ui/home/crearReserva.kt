package com.example.lacabanam.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.lacabanam.R
import com.example.lacabanam.databinding.FragmentCrearReservaBinding
import com.example.lacabanam.model.Reserva
import com.example.lacabanam.viewmodel.HomeViewModel


class crearReserva : Fragment() {
        private var _binding: FragmentCrearReservaBinding? = null
        private val binding get() = _binding!!
        private lateinit var reservaViewModel: HomeViewModel

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            reservaViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
            _binding = FragmentCrearReservaBinding.inflate(inflater, container, false)

            binding.btnCrear.setOnClickListener { agregarReserva() }


            return binding.root
        }

        //Efectivamente hace el registro del lugar en la base de datos
        private fun agregarReserva() {

            val habitacion = binding.etHabitacion.getSelectedItem().toString()

            val fechaInicio=binding.etFechaEntrada.text.toString()
            val fechaSalida=binding.etFechaSalida.text.toString()
            val actividades=binding.etActividades.getSelectedItem().toString()
            val cantidadPersonas = Integer.parseInt(binding.etCantidad.text.toString())
            val telefono = binding.etTelefono.text.toString()


            if (habitacion.isNotEmpty()) {   //Al menos tenemos un nombre
                val reserva = Reserva("", habitacion, fechaInicio, fechaSalida, actividades, cantidadPersonas, telefono)
                reservaViewModel.agregarReserva(reserva)
                Toast.makeText(requireContext(),"Exito", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_crearReserva_to_homeFragment)
            } else {  //No hay info del lugar...
                Toast.makeText(requireContext(), "faltan datos",
                    Toast.LENGTH_LONG).show()
            }
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }