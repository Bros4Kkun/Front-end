package com.example.RunToU

import retrofit2.Call
import okhttp3.MultipartBody
import retrofit2.http.*

interface TestService {
    @Headers("accept: application/json", "content-type: application/json") //헤더
    @POST("/api/user/signin") // 방식
    fun login(@Body params: HashMap<String, Any>): Call<SignUpOkResponse> //함수와 바디에 들어갈 값

    @GET("/api/signup/check")
    fun signUpCheck(
        @Query("email") email: String,
        @Query("password") password: String,
    ): Call<SignUpOkResponse>

    @Multipart
    @POST("/api/file")
    fun uploadImage(
        @Part file: MultipartBody.Part
    ): Call<SignUpOkResponse>

    @GET("/api/info/me")
    fun myInfo(@Header("access_token") accessToken: String): Call<SignUpOkResponse>

    @GET("/api/info/other")
    fun otherUserInfo(
        @Header("access_token") accessToken: String,
        @Query("user_uid") userUid: Int
    ): Call<SignUpOkResponse>
}