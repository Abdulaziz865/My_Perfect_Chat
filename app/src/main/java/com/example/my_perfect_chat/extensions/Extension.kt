package com.example.my_perfect_chat.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

const val CHILD_MESSAGE = "text"
const val CHILD_TYPE = "type"
const val CHILD_FROM = "from"
const val CHILD_TIME = "timeSoft"
const val CURRENT_ID = "id"
const val NODE_MESSAGE = "messages"
private val database = Firebase.database

const val ID: String = "VIP"
var allBall1: Int = 0
var allBall2: Int = 0
var allBall3: Int = 0
var allBall4: Int = 0
var allBall5: Int = 0
var allBall6: Int = 0
var allBall7: Int = 0
var allBall8: Int = 0


fun Fragment.showToast(text: String) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
}