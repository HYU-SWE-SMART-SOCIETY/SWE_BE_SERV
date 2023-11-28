package com.holme.be_app.api.sync.service

import com.holme.be_app.api.report.entity.Generate
import com.holme.be_app.api.report.entity.ReportRequestGenerate
import com.holme.be_app.entity.ReportType
import com.holme.be_app.repository.ServiceUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class sendReportService(
    @Autowired private val serviceUserRepository: ServiceUserRepository
) {

    fun sendReportRequest (
        user: String,
        reportType: ReportType,
        report: String
    ): Boolean {
        try{
            //* Find user id
            val serviceUser = serviceUserRepository.findByName(user) ?: throw Error("No Such User Exists!")
            val restTemplate = RestTemplate()
            val header = HttpHeaders()

            header.contentType = MediaType.APPLICATION_JSON
            val payload = Generate(serviceUser.id!!, reportType, report)
            val reportGenerateRequest = ReportRequestGenerate(payload)

            val resp: ResponseEntity<String> = restTemplate.postForEntity(
                "http://localhost:11000/api/v1/report/generate/ai",
                reportGenerateRequest,
                String::class.java
            )
            if(resp.statusCode.is2xxSuccessful){
                //* TODO: Need to handle Exception.
                return true
            }else{
                throw Error("StatusCode: ${resp.statusCode}")
            }

        }catch (e: Error) {
            println("ERROR while making report!! : ${e.message}")
            return false
        }
    }
}