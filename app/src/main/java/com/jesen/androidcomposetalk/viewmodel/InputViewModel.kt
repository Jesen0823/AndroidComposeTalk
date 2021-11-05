package com.jesen.androidcomposetalk.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * 登录注册页面状态保持
 * */
class InputViewModel : ViewModel() {

    var rePassword by mutableStateOf("")
    var mocId by mutableStateOf("")
    var orderId by mutableStateOf("")

    // 状态
    var name by mutableStateOf("")
    var pwd by mutableStateOf("")
    var isHide by mutableStateOf(false)

    // 密码是否展示明文
    var showPwd by mutableStateOf(true)

    // 事件
    fun onNameChange(str: String) {
        name = str
    }

    fun onPwdChange(str: String) {
        pwd = str
    }

    fun onFocusHide(hide: Boolean) {
        isHide = hide
    }

    fun onRePwdChange(str: String) {
        rePassword = str
    }

    fun onMocIdChange(str: String) {
        mocId = str
    }

    fun onOrderIdChange(str: String) {
        orderId = str
    }

    fun onShowPwdChange(show: Boolean) {
        showPwd = show
    }
}