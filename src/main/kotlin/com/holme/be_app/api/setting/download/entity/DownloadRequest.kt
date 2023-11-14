package com.holme.be_app.api.setting.download.entity

import com.holme.be_app.api.entity.request.SingleRequest

class DownloadRequestPayload (
    val userId: Int,
    val settingName: String,
)

class DownloadRequest (
    override val payload: DownloadRequestPayload
): SingleRequest<DownloadRequestPayload>(payload)