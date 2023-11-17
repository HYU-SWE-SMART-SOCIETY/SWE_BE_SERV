package com.holme.be_app.api.sync.entity

import kotlinx.serialization.Serializable

@Serializable // GoLang Naming Convention
data class Sync(val Ok: Int)
@Serializable // GoLang Naming Convention
data class SyncResult(val Result: List<Sync>)

class Substitute(
    val instId: Int,
    val substituteType: String,
    val isUpgrade: Boolean,
)

class SyncResponse(
    val ok: Boolean,
    val res: SyncResult?,
    val substituteList: List<Substitute>?,
    val message: String?
)