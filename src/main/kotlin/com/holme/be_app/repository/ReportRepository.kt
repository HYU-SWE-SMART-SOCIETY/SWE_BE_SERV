package com.holme.be_app.repository

import com.holme.be_app.entity.Report
import com.holme.be_app.entity.ReportType
import org.springframework.data.repository.CrudRepository

interface ReportRepository: CrudRepository<Report, Int> {
    fun findAllByUserId(userId: Int):MutableList<Report>?
    fun findAllByUserIdAndReportType(userId: Int, reportType: ReportType): MutableList<Report>?
}