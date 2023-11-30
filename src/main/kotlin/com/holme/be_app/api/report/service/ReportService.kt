package com.holme.be_app.api.report.service

import com.holme.be_app.api.report.entity.ReportResponse
import com.holme.be_app.dto.ReportDto
import com.holme.be_app.dto.toEntity
import com.holme.be_app.entity.Report
import com.holme.be_app.entity.ReportType
import com.holme.be_app.entity.toDto
import com.holme.be_app.repository.ReportRepository
import com.holme.be_app.repository.ServiceUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class ReportService (
    @Autowired val serviceUserRepository: ServiceUserRepository,
    @Autowired val reportRepository: ReportRepository
) {
    fun generateReport(userId: Int, reportType:ReportType, data: String): Boolean {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val current = LocalDateTime.now().format(formatter)
        return try{
            val report = ReportDto(null, payload = data, reportType, false, userId, current).toEntity(serviceUserRepository) ?: throw Error("No such user for current report.")
            reportRepository.save(report)
            true
        }catch (e: Error) {
            println(e.message)
            false
        }
    }

    fun checkReport(reportId: Int): Boolean {
        return try{
            if(reportRepository.updateCheckedByReportId(reportId,true) <= 0) throw Error("Error: Cannot Update")
            true
        }catch (e: Error) {
            println(e.message)
            false
        }
    }

    fun fetchAll(userId: Int): List<ReportResponse>? {
        return try{
            val reportList = reportRepository.findAllByUserId(userId) ?: return null //* return null if there is no reports

            //* List into ReportResponse -> Convert all reports into dto, fill it into List of ReportResponse.
            val returnList: List<ReportResponse> = reportList.map { ReportResponse(it.toDto()) }

            returnList
        }catch (e: Error) {
            println(e.message)
            null
        }
    }

    fun fetchType(userId: Int, reportType: ReportType): List<ReportResponse>? {
        return try{
            val reportList = reportRepository.findAllByUserIdAndReportType(userId, reportType) ?: return null //* return null if there is no reports

            //* List into ReportResponse -> Convert all reports into dto, fill it into List of ReportResponse.
            val returnList: List<ReportResponse> = reportList.map { ReportResponse(it.toDto()) }

            returnList
        }catch (e: Error) {
            println(e.message)
            null
        }
    }
}