package com.example.actividad2

import android.content.Context
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
import android.view.inputmethod.InputMethodManager
import com.example.actividad2.databinding.FragmentLoginBinding
import kotlin.math.log


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSubmit.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            // Validar el formato del email
            if (!isValidEmail(email)) {
                binding.email.error = "El email no tiene un formato válido"
                return@setOnClickListener //Que salga de la funcion
            }

            // Validar el formato de la contraseña
            if (!isValidPassword(password)) {
                binding.password.error = "La contraseña debe tener al menos 6 caracteres, una mayúscula y un número"
                return@setOnClickListener
            }

            // Si ambos campos tienen un formato válido, crear el bundle y mostrar el fragmento MostrarFragment
            val bundle = Bundle()
            bundle.putString("email", email)
            bundle.putString("password", password)

            val mostrar = MostrarFragment()
            mostrar.arguments = bundle

            activity?.let {
                it.supportFragmentManager.beginTransaction()
                    .replace(R.id.Container,mostrar).commit()
            }
        }
        binding.email.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

        binding.password.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

        binding.termsAndConditionsTextview.movementMethod = LinkMovementMethod.getInstance()

        val termsAndConditionsUrl = "http://www.google.com"
        val privacyPolicyUrl = "https://www.twitch.tv"

        val termsAndConditionsClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(termsAndConditionsUrl))
                startActivity(intent)
            }
        }

        val privacyPolicyClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(privacyPolicyUrl))
                startActivity(intent)
            }
        }

        val spannableString = SpannableString(binding.termsAndConditionsTextview.text)

        spannableString.setSpan(
            termsAndConditionsClickableSpan,
            32,
            52,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannableString.setSpan(
            privacyPolicyClickableSpan,
            57,
            71,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.termsAndConditionsTextview.text = spannableString





    }

    // Función para validar el formato del email
    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Función para validar el formato de la contraseña
    private fun isValidPassword(password: String): Boolean {
        val passwordRegex = "^(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$".toRegex()
        return password.matches(passwordRegex)
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