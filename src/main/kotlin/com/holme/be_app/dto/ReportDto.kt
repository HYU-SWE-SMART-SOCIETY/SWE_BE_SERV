package com.holme.be_app.dto

import com.holme.be_app.entity.Report
import com.holme.be_app.entity.ReportType
import com.holme.be_app.repository.ServiceUserRepository

class ReportDto (
    val id: Int?,
    val payload: String,
    val reportType: ReportType,
    val checked: Boolean,
    val userId: Int?
)

fun ReportDto.toEntity(
    serviceUserRepository: ServiceUserRepository
): Report? {
    val foundUser = userId?.let { serviceUserRepository.findById(it).orElse(null) } ?: return null

    return Report(
        this.id,
        this.payload,
        this.reportType,
        this.checked,
        user = foundUser
    )
}