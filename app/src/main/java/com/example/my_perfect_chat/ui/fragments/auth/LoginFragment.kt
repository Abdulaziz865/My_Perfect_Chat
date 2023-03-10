package com.example.my_perfect_chat.ui.fragments.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.my_perfect_chat.R
import com.example.my_perfect_chat.databinding.FragmentLoginBinding
import com.example.my_perfect_chat.extensions.ID
import com.example.my_perfect_chat.extensions.showToast
import com.example.my_perfect_chat.utils.SharedPreferenceUtil
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var auth: FirebaseAuth
    private val binding by viewBinding(FragmentLoginBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
    }

    private fun initialize() {
        auth = FirebaseAuth.getInstance()
    }

    private fun setupListener() = with(binding) {
        btnBackInSignUp.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }
        loginBtn.setOnClickListener {
            val email = loginEmailPerson.text.toString()
            val password = loginPasswordPerson.text.toString()
            val username = loginUserName.text.toString()
            val id = loginIdPerson.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && username.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener() {

                    if (it.isSuccessful && id.isEmpty()) {
                        findNavController().navigate(R.id.homeFragment)
                        SharedPreferenceUtil.isProver = false
                        SharedPreferenceUtil.isColor = false

                    } else if (it.isSuccessful && id == "report/") {
                        showToast("Rapid/")
                        findNavController().navigate(R.id.homeFragment)
                        SharedPreferenceUtil.isProver = true
                        SharedPreferenceUtil.dataAdmin = ID

                    } else if (it.isSuccessful && id == "external/") {
                        showToast("General/")
                        findNavController().navigate(R.id.homeFragment)
                        SharedPreferenceUtil.isColor = true
                        SharedPreferenceUtil.dataAdmin = ID

                    } else {
                        binding.loginEmailPerson.error = it.exception.toString()
                    }
                }
            } else {
                showToast("Пусто")
            }
        }
    }
}