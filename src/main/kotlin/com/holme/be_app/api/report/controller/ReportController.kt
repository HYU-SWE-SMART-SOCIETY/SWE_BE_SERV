package com.holme.be_app.api.report.controller

import com.holme.be_app.api.entity.response.MultipleResponse
import com.holme.be_app.api.entity.response.MultipleResponseService
import com.holme.be_app.api.entity.response.SingleResponse
import com.holme.be_app.api.entity.response.SingleResponseService
import com.holme.be_app.api.report.entity.ReportRequestFetchAll
import com.holme.be_app.api.report.entity.ReportRequestGenerate
import com.holme.be_app.api.report.entity.ReportRequestWithType
import com.holme.be_app.api.report.entity.ReportResponse
import com.holme.be_app.api.report.service.ReportService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/report")
class ReportController (
    @Autowired val reportService: ReportService,
    @Autowired val singleResponseService: SingleResponseService<ReportResponse>,
    @Autowired val multipleResponseService: MultipleResponseService<ReportResponse>
) {
    @PostMapping("/generate")
    fun handleReportGenerate(@RequestBody reportRequestGenerate: ReportRequestGenerate): SingleResponse<ReportResponse> {
        return try{
            val userId = reportRequestGenerate.payload.userId
            val reportType = reportRequestGenerate.payload.reportType
            val data = reportRequestGenerate.payload.report

            if(!reportService.generateReport(userId,reportType,data)) throw Error("Error! Failed to create report")

            singleResponseService.isSuccessful("", null) //TODO
        }catch (e: Error){
            val errorMsg: String = if(e.message is String) e.message!! else e.toString()
            singleResponseService.isFailure(-1,errorMsg, null)
        }
    }

    @PostMapping("/fetchAll")
    fun handleReportFetchAll(@RequestBody reportRequestFetchAll: ReportRequestFetchAll): MultipleResponse<ReportResponse> {
        return try{
            val userId = reportRequestFetchAll.payload.userId
            val reports = reportService.fetchAll(userId)

            multipleResponseService.isSuccessful("", reports) //TODO
        }catch (e: Error){
            val errorMsg: String = if(e.message is String) e.message!! else e.toString()
            multipleResponseService.isFailure(-1,errorMsg, null)
        }
    }

    @PostMapping("/withType")
    fun handleReportWithType(@RequestBody reportRequestWithType: ReportRequestWithType): MultipleResponse<ReportResponse> {
        return try{

            multipleResponseService.isSuccessful("", null) //TODO
        }catch (e: Error){
            val errorMsg: String = if(e.message is String) e.message!! else e.toString()
            multipleResponseService.isFailure(-1,errorMsg, null)
        }
    }
}