package com.example.my_perfect_chat.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlin.random.Random

abstract class BaseFragment(private val layoutId: Int) : Fragment() {
    val pageId = Random.nextLong(2324636, 535623462234513)
    var pagePos = 0
    protected lateinit var fragmentReplacer: FragmentReplacer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    fun setPageInfo(pagePos: Int, fragmentReplacer: FragmentReplacer) {
        this.pagePos = pagePos
        this.fragmentReplacer = fragmentReplacer
    }
}