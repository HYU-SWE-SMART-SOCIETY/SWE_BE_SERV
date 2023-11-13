package com.holme.be_app.api.setting.upload.entity

import com.holme.be_app.api.entity.instance.Instance
import com.holme.be_app.api.entity.request.MultipleRequests
import com.holme.be_app.api.entity.request.SingleRequest

class UploadSettings<T: Any>(
    val instanceType: Int,
    override val payload: T
): SingleRequest<T>(payload)

class UploadRequest<T: Instance>(
    val userId: Int,
    val settingId: Int?,
    override val payloads: List<UploadSettings<T>>,
): MultipleRequests<T>(payloads)