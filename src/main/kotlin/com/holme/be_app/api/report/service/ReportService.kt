package com.holme.be_app.api.report.service

import com.holme.be_app.dto.ReportDto
import com.holme.be_app.dto.toEntity
import com.holme.be_app.entity.ReportType
import com.holme.be_app.repository.ReportRepository
import com.holme.be_app.repository.ServiceUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReportService (
    @Autowired val serviceUserRepository: ServiceUserRepository,
    @Autowired val reportRepository: ReportRepository
) {
    fun generateReport(userId: Int, reportType:ReportType, data: String): Boolean {
        return try{
            val report = ReportDto(null, payload = data, reportType,userId).toEntity(serviceUserRepository) ?: throw Error("No such user for current report.")
            reportRepository.save(report)
            true
        }catch (e: Error) {
            println(e.message)
            false
        }
    }
}