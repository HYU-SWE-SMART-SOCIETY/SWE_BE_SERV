package com.holme.be_app.entity.request

class SingleRequest<T>(
    override val payload: T
) : ServiceRequest<T>