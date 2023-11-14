package com.holme.be_app.api.report.entity

import com.holme.be_app.api.entity.request.SingleRequest
import com.holme.be_app.entity.ReportType

class Generate(
    val userId: Int,
    val reportType: ReportType,
    val report: String
)
class FetchAll(
    val userId: Int
)

class WithType (
    val userId: Int,
    val reportType: ReportType
)

class ReportRequestGenerate(
    override val payload: Generate
):SingleRequest<Generate>(payload)

class ReportRequestFetchAll (
    override val payload: FetchAll
): SingleRequest<FetchAll>(payload)

class ReportRequestWithType (
    override val payload: WithType
): SingleRequest<WithType>(payload)