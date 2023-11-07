package com.holme.be_app.api.entity.request

interface ServiceRequest<T> {
    val payload: T
}