package com.holme.be_app.api.sync.entity

import com.holme.be_app.entity.request.MultipleRequests
import com.holme.be_app.entity.request.SingleRequest

class SyncRequest<T: Instance> (
    val user: String,
    override val payloads: List<SingleSyncRequest<T>>
): MultipleRequests<T>(payloads)

class SendSyncRequest ( //* Send Request to Hivemind
    var instance: Int,
    payload: String
): SingleRequest<String>(payload)

class SendHBRequest (
    val user: String,
    val request: MutableList<SendSyncRequest>
)

class SingleSyncRequest<T> (
    val instanceType: Int,
    payload: T
): SingleRequest<T>(payload)