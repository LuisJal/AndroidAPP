package com.example.actividad2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.example.actividad2.databinding.FragmentInicioBinding


class InicioFragment : Fragment() {
    private var _binding: FragmentInicioBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInicioBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.login.movementMethod = LinkMovementMethod.getInstance()
        val loginClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                activity?.let { it.supportFragmentManager.beginTransaction()
                    .replace(R.id.Container,LoginFragment()).commit() }
            }
        }

        val spannableString = SpannableString(binding.login.text)
        spannableString.setSpan(
            loginClickableSpan,
            20,
            25,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.login.text = spannableString

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