package com.example.my_perfect_chat.ui.fragments.basis

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.my_perfect_chat.R
import com.example.my_perfect_chat.databinding.FragmentProfileBinding
import com.example.my_perfect_chat.utils.BaseFragment

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private val binding by viewBinding(FragmentProfileBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}