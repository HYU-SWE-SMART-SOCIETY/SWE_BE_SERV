package com.holme.be_app.api.entity.response

class MultipleResponse<T>(
    override var ok: Boolean,
    override var code: Int,
    override var message: String,
    override var data: MutableList<T>?
): ServiceResponse<MutableList<T>?> {
    override fun setVal(ok: Boolean, code: Int, message: String, data: MutableList<T>?) {
        this.ok = ok
        this.code = code
        this.message = message
        this.data = data
    }
}