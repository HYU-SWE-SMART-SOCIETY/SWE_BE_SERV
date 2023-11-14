package com.holme.be_app.api.ping.entity

import com.holme.be_app.api.entity.instance.InstanceType

class PingResponse (
    val ok: Boolean,
    val message: String?,
    val state:Map<InstanceType,Boolean>
)