package com.holme.be_app.api.sync.entity

import com.holme.be_app.entity.request.MultipleRequests
import com.holme.be_app.entity.request.SingleRequest

class SyncRequest<T> (
    override val payloads: List<SingleSyncRequest<T>>
): MultipleRequests<T>(payloads)

class SingleSyncRequest<T> (
    val instanceType: Int,
    payloads: T
): SingleRequest<T>(payloads)