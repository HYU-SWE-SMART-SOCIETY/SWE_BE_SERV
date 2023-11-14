package com.holme.be_app.api.entity.response

class MultipleResponse<T>(
    override var ok: Boolean,
    override var code: Int,
    override var message: String,
    override var data: List<T>?
): ServiceResponse<List<T>?> {
    override fun setVal(ok: Boolean, code: Int, message: String, data: List<T>?) {
        this.ok = ok
        this.code = code
        this.message = message
        this.data = data
    }
}