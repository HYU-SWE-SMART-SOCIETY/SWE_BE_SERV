package com.holme.be_app.api.entity.response

class MultipleResponseService<T> {
    fun isSuccessful(
        message: String?,
        data: MutableList<T>?
    ): MultipleResponse<T> {
        return if(message == null){
            MultipleResponse<T>(true,1, "Request Successful", data)
        }else {
            MultipleResponse<T>(true,1, message, data)
        }
    }

    fun isFailure(
        code: Int,
        message: String,
        data: MutableList<T>?
    ): MultipleResponse<T> {
        return MultipleResponse<T>(false, code, message, data)
    }

}