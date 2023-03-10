package com.example.my_perfect_chat.utils

interface FragmentReplacer {

    fun replace(position: Int, newFragment: BaseFragment, isNotify: Boolean = true)
    fun replaceDef(position: Int, isNotify: Boolean = true) : BaseFragment
    fun replaceCurrentToDef()

    var lastReplacedPos: Int
}