package com.example.my_perfect_chat.ui.fragments.basis

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.my_perfect_chat.R
import com.example.my_perfect_chat.databinding.FragmentHomeBinding
import com.example.my_perfect_chat.ui.adapter.MyViewPager2Adapter
import com.example.my_perfect_chat.utils.SharedPreferenceUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val db = Firebase.firestore
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        onSaveCash()
        proverka()
    }

    private fun initialize() {
        auth = FirebaseAuth.getInstance()
        tabLayout()
    }

    private fun proverka() = with(binding) {
        if (SharedPreferenceUtil.isProver) {
            sC.text = SharedPreferenceUtil.dataAdmin.toString()
            sC.visibility = View.VISIBLE
        }
    }

    private fun tabLayout() {
        binding.viewPager2.adapter = MyViewPager2Adapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Ball"
                }
                1 -> {
                    tab.text = "Chat"
                }
                2 -> {
                    tab.text = "Profile"
                }
            }
        }.attach()
    }

    private fun onSaveCash() {
        SharedPreferenceUtil.isPreference = true
    }
}