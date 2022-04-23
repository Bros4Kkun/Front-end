package com.example.RunToU

import okhttp3.ResponseBody

class ErrorUtils {
    companion object {
        inline fun <reified T> getErrorResponse(errorBody: ResponseBody): T? {
            return error("fuck")
        }
    }
}