package com.holme.be_app.api.setting.download.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.holme.be_app.api.entity.response.SingleResponse
import com.holme.be_app.api.entity.response.SingleResponseService
import com.holme.be_app.api.setting.download.entity.DownloadRequest
import com.holme.be_app.api.setting.download.entity.DownloadResponse
import com.holme.be_app.api.setting.download.service.DownloadService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/setting/download")
class DownloadController(
    @Autowired val downloadService: DownloadService,
    @Autowired val responseService: SingleResponseService<DownloadResponse>
) {

    @PostMapping("/")
    fun handleSettingDownloadRequest(@RequestBody downloadRequest: DownloadRequest): SingleResponse<DownloadResponse> {
        return try {
            val userId = downloadRequest.payload.userId
            val settingName = downloadRequest.payload.settingName.trim()

            val downloadPayload = downloadService.downloadSettings(userId,settingName) ?: throw Error("Setting Cannot be found")

            responseService.isSuccessful(null,downloadPayload)// TODO Add data payload
        }catch (e: Error) {
            val errorMsg: String = if(e.message is String) e.message!! else e.toString()
            responseService.isFailure(-1, errorMsg, null)
        }
    }

}