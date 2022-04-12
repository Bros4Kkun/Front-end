package com.example.RunToU



class UserAPIMethods {

    companion object {

        fun signUpCheck(
            email: String,
            password: String,
            callback: (response: SignUpOkResponse?) -> Unit
        ) {

            RetrofitService.test.signUpCheck(
                email,
                password
            ).enqueue(
                MethodCallback.generalCallback<SignUpCheckResponse, SignUpCheckOkResponse, SignUpCheckErrorResponse>(
                    callback
                )
            )
        }
    }

}