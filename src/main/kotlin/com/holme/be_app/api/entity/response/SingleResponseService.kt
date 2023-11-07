package com.holme.be_app.api.entity.response

import org.springframework.stereotype.Service

@Service
class SingleResponseService <T> {
    fun isSuccessful(
        message: String?
    ): SingleResponse<T> {
        return if(message == null){
            SingleResponse<T>(true,1, "Request Successful")
        }else {
            SingleResponse<T>(true,1, message)
        }
    }

    fun isFailure(
        code: Int,
        message: String
    ): SingleResponse<T> {
        return SingleResponse<T>(false, code, message)
    }

}