package com.holme.be_app.api.entity.response

interface ServiceResponse {
    val ok: Boolean
    val code: Int
    val message: String
    val data: Any?

    fun setVal(ok:Boolean, code:Int, message:String, data: Any?)
}