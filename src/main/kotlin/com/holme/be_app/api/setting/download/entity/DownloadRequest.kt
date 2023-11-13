package com.holme.be_app.api.setting.download.entity

import com.holme.be_app.api.entity.request.SingleRequest

class DownloadRequest (
    val userId: Int
)

class SingleDownloadRequest (
    override val payload: DownloadRequest
): SingleRequest<DownloadRequest>(payload)