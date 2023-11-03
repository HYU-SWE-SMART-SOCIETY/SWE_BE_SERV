package com.holme.be_app.entity.request

open class SingleRequest<T>(
    override val payload: T
) : ServiceRequest<T>