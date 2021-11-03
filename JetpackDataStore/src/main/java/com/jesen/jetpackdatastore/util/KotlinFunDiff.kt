package com.jesen.jetpackdatastore.util

import android.content.Intent

fun testFun() {
    var number: Int? = null

}

fun baseFun(num: Int?) {
    if (num != null) {
        val sum = num + 1
    }
}

fun letFun(num: Int?) {
    val sum = num?.let { it + 1 } ?: 0
}

// also  前面执行后继续执行后面，返回also之前的结果
var i = 1
fun alseFun() = (i * i).also {
    i++
}

// applay对大量重复操作，返回的是被调用对象
fun applayFun(){
    val intent = Intent().apply {
        putExtra("param1",1)
        putExtra("param2",2)
        action = "com.pp.Mo"
    }
}

// run对大量重复操作，不返回的被调用对象，返回最后一行
fun runFun(){
    val intent = Intent().run {
        putExtra("param1",1)
        putExtra("param2",2)
        action = "com.pp.Mo"
    }
}