package com.example.my_perfect_chat

import android.app.Application
import com.example.my_perfect_chat.utils.SharedPreferenceUtil

class App : Application(){

    override fun onCreate() {
        super.onCreate()
        SharedPreferenceUtil.unitProver(this)
        SharedPreferenceUtil.units(this)
        SharedPreferenceUtil.unitsData(this)
    }
}