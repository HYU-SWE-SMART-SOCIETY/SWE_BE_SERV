package com.holme.be_app.entity.response

import org.springframework.stereotype.Service

@Service
class SingleResponseService <T> {
    fun isSuccessful(): SingleResponse<T> {
        return SingleResponse<T>(true,1, "Request Successful")
    }

    fun isFailure(
        code: Int,
        message: String
    ): SingleResponse<T> {
        return SingleResponse<T>(false, code, message)
    }

}