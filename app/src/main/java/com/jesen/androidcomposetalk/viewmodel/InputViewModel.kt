package com.jesen.androidcomposetalk.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class InputViewModel : ViewModel() {
    // 状态
    var name by mutableStateOf("")
    var pwd by mutableStateOf("")
    var isHide by mutableStateOf(false)

    // 事件
    fun onNameChange(str: String) {
        name = str
    }

    fun onPwdChange(str: String) {
        pwd = str
    }

    fun onFocusHide(hide:Boolean){
        isHide = hide
    }
}