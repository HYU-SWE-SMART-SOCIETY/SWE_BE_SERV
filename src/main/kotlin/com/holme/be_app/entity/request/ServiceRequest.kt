package com.holme.be_app.entity.request

interface ServiceRequest<T> {
    val payload: T
}