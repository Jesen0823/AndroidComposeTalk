package com.jesen.paging3demo

import com.google.gson.annotations.SerializedName

/**
 * 驾照考试题库
 *  接口数据来源：https://wx.jdcloud.com/market/api/11865
 * */
data class ExamQuestionBank(

    val charge: Boolean,
    val code: String,      // 状态码 10000表示OK
    val msg: String,
    val requestId: String,
    val result: Result?
)

data class Result(
    val msg: String,
    @SerializedName("result")
    val resultData: ResultInfo?,
    val status: Int
)

data class ResultInfo(
    val chapter: Int,
    @SerializedName("list")
    val questionList: List<Question>?, // 题目列表
    val pagenum: Int,                 // 页码
    val pagesize: Int,                // 每页大小
    val sort: String,                 // 排序方式
    val subject: Int,                 // 科目类别
    val total: Int,                   // 总数
    val type: String                  // 题目类型
)

data class Question(
    val answer: String,
    val chapter: String?,
    val explain: String,    // 解析
    val option1: String?,    // 选项A
    val option2: String?,    // 选项B
    val option3: String?,    // 选项C
    val option4: String?,    // 选项D
    val question: String,   // 答案
    val type: String
)