package com.jesen.androidcomposetalk.network

import com.jesen.androidcomposetalk.model.UserResult
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * 注册API
 * [Flut] 请求url:https://api.devio.org/uapi/user/registration?userName=jert&password=yt5678&imoocId=122&orderId=2586
 * [Flut] 请求头:{auth-token: ZmEtMjAyMS0wNC0xMiAyMToyMjoyMC1mYQ==fa, course-flag: fa}
 * [Flut] 请求参:{userName: jert, password: yt5678, imoocId: 122, orderId: 2586}
 *
 * response: {"code":5004,"msg":"请先购买课程@https://coding.imooc.com/class/487.html"}
 *
 * */
interface RegisterServiceApi :BaseApi{

    @POST("/uapi/user/registration")
    suspend fun getDataFromApi(
        @Query("userName") userName: String,
        @Query("password") password: String,
        @Query("imoocId") imoocId: Int,
        @Query("orderId") orderId: Int,
    ): UserResult

    override fun needLogin(): Boolean = false
}
