package com.example.lacabanam.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lacabanam.R
import com.example.lacabanam.ui.home.actualizaReservaArgs
import com.example.lacabanam.databinding.FragmentActualizaReservaBinding
import com.example.lacabanam.model.Reserva
import com.example.lacabanam.viewmodel.HomeViewModel


class actualizaReserva : Fragment() {
    //recupere argumentos

    private val args by navArgs<actualizaReservaArgs>()
    private var _binding : FragmentActualizaReservaBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentActualizaReservaBinding.inflate(inflater, container, false)

        binding.etTipoHabitacion.setText(args.reserva.nombreHabitacion)
        binding.etFechaEntrada.setText(args.reserva.fechaInicio)
        binding.etFechaSalida.setText(args.reserva.fechaSalida)
        binding.etActividades.setText(args.reserva.actividades)
        binding.etCantidad.setText(args.reserva.cantidadPersonas.toString())
        binding.etTelefono.setText(args.reserva.telefono)

        binding.btnActualizar.setOnClickListener{
            actualizarReserva()
        }
        binding.btnEliminar.setOnClickListener{
            eliminarReserva()
        }




        return binding.root
    }

    private fun actualizarReserva(){

        val habitacion = binding.etTipoHabitacion.text.toString()
        val fechaInicio=binding.etFechaEntrada.text.toString()
        val fechaSalida=binding.etFechaSalida.text.toString()
        val actividades=binding.etActividades.text.toString()
        val cantidadPersonas = Integer.parseInt(binding.etCantidad.text.toString())
        val telefono = binding.etTelefono.text.toString()


        if (habitacion.isEmpty()){
            Toast.makeText(requireContext(), getString(R.string.msg_data), Toast.LENGTH_LONG)

        } else if (fechaInicio.isEmpty()){
            Toast.makeText(requireContext(), getString(R.string.msg_data), Toast.LENGTH_LONG)
        }
        else{
            val reserva = Reserva(args.reserva.id, habitacion, fechaInicio, fechaSalida, actividades, cantidadPersonas, telefono)
            homeViewModel.agregarReserva(reserva)
            Toast.makeText(requireContext(), getString(R.string.msg_reserva_actualizada), Toast.LENGTH_LONG)
            findNavController().navigate(R.id.action_actualizaReserva_to_homeFragment)
        }


    }


    private fun eliminarReserva(){
        val habitacion = binding.etTipoHabitacion.text.toString()
        val fechaInicio=binding.etFechaEntrada.text.toString()
        val fechaSalida=binding.etFechaSalida.text.toString()
        val actividades=binding.etActividades.text.toString()
        val cantidadPersonas = Integer.parseInt(binding.etCantidad.text.toString())
        val telefono = binding.etTelefono.text.toString()

        if (habitacion.isEmpty()){
            Toast.makeText(requireContext(), getString(R.string.msg_data), Toast.LENGTH_LONG)

        } else if (fechaInicio.isEmpty()){
            Toast.makeText(requireContext(), getString(R.string.msg_data), Toast.LENGTH_LONG)
        }
        else{
            val reserva = Reserva(args.reserva.id, habitacion, fechaInicio, fechaSalida, actividades, cantidadPersonas, telefono)
            homeViewModel.eliminarReserva(reserva)
            Toast.makeText(requireContext(), getString(R.string.msg_reserva_eliminada), Toast.LENGTH_LONG)
            findNavController().navigate(R.id.action_actualizaReserva_to_homeFragment)
        }


    }
}