package com.holme.be_app.repository

import com.holme.be_app.entity.Report
import com.holme.be_app.entity.ReportType
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface ReportRepository: CrudRepository<Report, Int> {
    fun findAllByUserId(userId: Int):MutableList<Report>?
    fun findAllByUserIdAndReportType(userId: Int, reportType: ReportType): MutableList<Report>?

    @Transactional
    @Modifying
    @Query("UPDATE Report r SET r.checked = :checked WHERE r.id = :reportId")
    fun updateCheckedByReportId(@Param("reportId") reportId: Int, @Param("checked") checked: Boolean): Int
}