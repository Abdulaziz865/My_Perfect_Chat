package com.example.my_perfect_chat.ui.fragments.test.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.my_perfect_chat.R
import com.example.my_perfect_chat.databinding.FragmentRedactBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RedactFragment : Fragment(R.layout.fragment_redact) {

    private val binding by viewBinding(FragmentRedactBinding::bind)
    private val database = Firebase.database

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
    }
}