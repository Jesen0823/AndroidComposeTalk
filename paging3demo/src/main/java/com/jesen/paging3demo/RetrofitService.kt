package com.jesen.paging3demo
// 接口：https://way.jd.com/jisuapi/driverexamQuery?type=c1&subject=1&pagesize=20&pagenum=1&sort=normal&appkey=647fd7f08ee823199d919a2a09161345
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * type：题目类型 分为A1,A3,B1,A2,B2,C1,C2,C3,D,E,F 默认C1
 * subject：科目类别 1为科目一 4为科目四 默认1
 * pagesize：每页数量 默认1
 * pagenum：当前页数
 * sort：排序方式 正常排序normal 随机排序rand 默认normal
 *
 * API来源： https://www.free-api.com/doc/470
 * */

interface RetrofitService {

    @GET("jisuapi/driverexamQuery")
    suspend fun getDataFromApi(
        @Query("pagenum") pagenum: Int,
        @Query("type") type: String = "C1",
        @Query("subject") subject: Int = 1,
        @Query("pagesize") pagesize: Int = 10,
        @Query("sort") sort: String = "normal",
        @Query("appkey") appkey: String = APP_KEY,
    ): ExamQuestionBank

    companion object {
        fun getApi(): RetrofitService {
            return RetrofitInstance.getRetroInstance().create(RetrofitService::class.java)
        }
    }
}