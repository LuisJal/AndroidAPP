package com.example.actividad2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.example.actividad2.databinding.FragmentInicioBinding
import com.example.actividad2.databinding.FragmentLoginBinding
import com.example.actividad2.databinding.FragmentMostrarBinding


class MostrarFragment : Fragment() {

    private var _binding: FragmentMostrarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMostrarBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val email = arguments?.getString("email")
        val password = arguments?.getString("password")
        binding.texto1.text=email
        binding.texto2.text=password

        val inicio = InicioFragment()

        binding.btnBack.setOnClickListener{
            activity?.let {
                it.supportFragmentManager.beginTransaction()
                    .replace(R.id.Container,inicio).commit()
            }
        }


    }



    override fun onOptionsItemSelected (item: MenuItem): Boolean {
        if (item.itemId == android. R.id.home) {activity?.onBackPressedDispatcher?.onBackPressed()}
        return super .onOptionsItemSelected(item)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}