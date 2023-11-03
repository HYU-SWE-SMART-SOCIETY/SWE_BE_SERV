package com.holme.be_app.entity.request

open class MultipleRequests<T> (
    open val payloads: List<SingleRequest<T>>
)