package com.holme.be_app.api.entity.request

open class MultipleRequests<T> (
    open val payloads: List<SingleRequest<T>>
)