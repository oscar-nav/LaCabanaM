package com.example.lacabanam.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lacabanam.R
import com.example.lacabanam.adapter.ReservaAdapter
import com.example.lacabanam.databinding.FragmentHomeBinding
import com.example.lacabanam.model.Reserva
import com.example.lacabanam.viewmodel.HomeViewModel


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btnAddReserva.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_crearReserva)
        }

        //Cargar datos
        val reservaAdaper = ReservaAdapter()
        val reciclador = binding.reciclador
        reciclador.adapter = reservaAdaper
        reciclador.layoutManager = LinearLayoutManager(requireContext())

        homeViewModel.obtenerReservas.observe(viewLifecycleOwner){
                reservas -> reservaAdaper.setReservas(reservas)
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}