package com.holme.be_app.api.ping.entity

import com.holme.be_app.api.entity.request.SingleRequest

class PingRequest (
    val userId: Int
)

class SinglePingRequest(
    override val payload: PingRequest
): SingleRequest<PingRequest>(payload)