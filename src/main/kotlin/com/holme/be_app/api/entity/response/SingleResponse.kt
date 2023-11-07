package com.holme.be_app.api.entity.response

class SingleResponse<T>(
    override var ok: Boolean,
    override var code: Int,
    override var message: String

): ServiceResponse {
    override fun setVal(ok: Boolean, code: Int, message: String) {
        this.ok = ok
        this.code = code
        this.message = message
    }
}