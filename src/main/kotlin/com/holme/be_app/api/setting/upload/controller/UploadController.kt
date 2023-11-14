package com.holme.be_app.api.setting.upload.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.holme.be_app.api.entity.instance.Instance
import com.holme.be_app.api.entity.response.SingleResponse
import com.holme.be_app.api.entity.response.SingleResponseService
import com.holme.be_app.api.setting.upload.entity.UploadRequest
import com.holme.be_app.api.setting.upload.entity.UploadResponse
import com.holme.be_app.api.setting.upload.service.UploadService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

//* Endpoint for upload setting.

@RestController
@RequestMapping("/api/v1/setting/upload")
class UploadController(
    @Autowired val uploadService: UploadService,
    @Autowired val responseService: SingleResponseService<UploadResponse>
) {
    @PostMapping("/")
    fun handleSettingUploadRequest(@RequestBody uploadRequest: UploadRequest<in Instance>): SingleResponse<UploadResponse>{

        return try{
            val userId = uploadRequest.userId
            val settingName = uploadRequest.settingName.trim()
            val settingId = uploadRequest.settingId
            val stringifiedPayload: String = ObjectMapper().writeValueAsString(uploadRequest.payloads)

            if(!uploadService.uploadSetting(userId, settingName, settingId, stringifiedPayload)) throw Error("Error! Failed to upload setting")

            responseService.isSuccessful(null,null)
        }catch (e: Error) {
            val errorMsg: String = if(e.message is String) e.message!! else e.toString()
            responseService.isFailure(-1, errorMsg, uploadRequest)
        }

    }
}