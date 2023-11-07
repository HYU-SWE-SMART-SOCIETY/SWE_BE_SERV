package com.holme.be_app.api.entity.response

import org.springframework.stereotype.Service

@Service
class SingleResponseService <T> {
    fun isSuccessful(
        message: String?,
        data: Any?
    ): SingleResponse<T> {
        return if(message == null){
            SingleResponse<T>(true,1, "Request Successful", data)
        }else {
            SingleResponse<T>(true,1, message, data)
        }
    }

    fun isFailure(
        code: Int,
        message: String,
        data: Any?
    ): SingleResponse<T> {
        return SingleResponse<T>(false, code, message, data)
    }

}