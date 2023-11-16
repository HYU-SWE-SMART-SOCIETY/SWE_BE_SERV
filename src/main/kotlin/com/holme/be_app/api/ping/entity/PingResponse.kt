package com.holme.be_app.api.ping.entity

import com.holme.be_app.api.entity.instance.InstanceType
import kotlinx.serialization.Serializable

class PingResponse (
    val ok: Boolean,
    val message: String?,
    val state:Map<InstanceType,Boolean>
)

@Serializable // GoLang Naming Convention
data class Ping(val InstanceId: Int, val IsExist: Boolean)
@Serializable // GoLang Naming Convention
data class PingResult(val Result: List<Ping>)