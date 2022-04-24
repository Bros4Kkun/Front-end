package com.example.RunToU

data class SignUpOkResponse(
    val success : Boolean,
    val accountId : String,
    val realName : String,
    val nickname : String,
    val phoneNumber : String,
    val accountNumber : String,
    val role : String
)
