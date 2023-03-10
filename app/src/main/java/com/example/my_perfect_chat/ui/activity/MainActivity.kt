package com.example.my_perfect_chat.ui.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.my_perfect_chat.R
import com.example.my_perfect_chat.databinding.ActivityMainBinding
import com.example.my_perfect_chat.ui.fragments.test.main.ConnectionLiveData
import com.example.my_perfect_chat.utils.SharedPreferenceUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var cld : ConnectionLiveData
    private lateinit var controllerNav: NavController
    private lateinit var auth: FirebaseAuth
    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkNetworkConnection()
        supportActionBar?.hide()
        auth = Firebase.auth
        setSupportActionBar(binding.toolbar)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        controllerNav = navHostFragment.navController

        when (SharedPreferenceUtil.isPreference) {
            true -> {
                controllerNav.navigate(R.id.homeFragment)
            }
            else -> {
                controllerNav.navigate(R.id.signUpFragment)
            }
        }
    }

    private fun checkNetworkConnection() {
        cld = ConnectionLiveData(application)

        cld.observe(this) { isConnected ->
            if (isConnected) {
                binding.toolbar.setBackgroundColor(Color.parseColor("#056052"))
                binding.cardProject.setBackgroundColor(Color.parseColor("#056052"))
                binding.wifiOff.visibility = View.INVISIBLE
                binding.wifiOn.visibility = View.VISIBLE
            }
            else {
                binding.toolbar.setBackgroundColor(Color.parseColor("#FF690808"))
                binding.cardProject.setBackgroundColor(Color.parseColor("#FF690808"))
                binding.wifiOff.visibility = View.VISIBLE
                binding.wifiOn.visibility = View.INVISIBLE
            }
        }
    }
}