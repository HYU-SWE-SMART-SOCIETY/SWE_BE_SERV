package com.holme.be_app.entity.request

class MultipleRequests<T> (
    val payloads: List<SingleRequest<T>>
)