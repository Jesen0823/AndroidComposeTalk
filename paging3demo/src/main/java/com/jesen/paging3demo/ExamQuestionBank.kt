package com.jesen.paging3demo

/**
 * 驾照考试题库
 *  接口数据来源：https://wx.jdcloud.com/market/api/11865
 * */
data class ExamQuestionBank (

    val code : Int,
    val charge : Boolean,
    val msg : String,
    val result : DataResult,
    val requestId : String
)

data class DataResult (

    val status : Int,
    val msg : String,
    val result : List<Question>
)

data class Question (

    val question : String,
    val option1 : String?,
    val option2 : String?,
    val option3 : String?,
    val option4 : String?,
    val answer : String,
    val explain : String,
    val type : String,
    val chapter : String
)
