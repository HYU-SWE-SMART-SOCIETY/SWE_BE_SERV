package com.holme.be_app.api.entity.request

open class SingleRequest<T>(
    override val payload: T
) : ServiceRequest<T>