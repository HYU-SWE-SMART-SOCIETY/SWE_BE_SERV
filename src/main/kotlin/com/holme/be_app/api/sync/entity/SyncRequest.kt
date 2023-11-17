package com.holme.be_app.api.sync.entity

import com.holme.be_app.api.entity.instance.Instance
import com.holme.be_app.api.entity.request.MultipleRequests
import com.holme.be_app.api.entity.request.SingleRequest

class ConnectedDevice (
    val instanceId: Int,
    val isExist: Boolean
)
class SyncRequest<T: Instance> (
    val user: String,
    val connectedDevices: List<ConnectedDevice>,
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