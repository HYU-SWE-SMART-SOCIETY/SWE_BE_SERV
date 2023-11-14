package com.holme.be_app.api.entity.response

interface ServiceResponse<T> {
    val ok: Boolean
    val code: Int
    val message: String
    val data: T

    fun setVal(ok:Boolean, code:Int, message:String, data: T)
}