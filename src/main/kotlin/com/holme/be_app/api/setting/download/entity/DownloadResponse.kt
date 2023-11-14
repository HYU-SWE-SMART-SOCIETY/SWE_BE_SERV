package com.holme.be_app.api.setting.download.entity

class DownloadResponse (
    val ok: Boolean,
    val message: String?,
    val payload: DownloadPayload?
)

class DownloadPayload (
    val settingName: String,
    val instanceSetting: String
)