package com.holme.be_app.api.setting.upload.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.holme.be_app.api.entity.instance.Instance
import com.holme.be_app.api.entity.response.SingleResponse
import com.holme.be_app.api.entity.response.SingleResponseService
import com.holme.be_app.api.setting.upload.entity.UploadRequest
import com.holme.be_app.api.setting.upload.entity.UploadResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

//* Endpoint for upload setting.

@RestController
@RequestMapping("/api/v1/setting/upload")
class UploadController(
    @Autowired val responseService: SingleResponseService<UploadResponse>
) {
    @PostMapping("/")
    fun handleSettingUploadRequest(@RequestBody uploadRequest: UploadRequest<in Instance>): SingleResponse<UploadResponse>{

        try{
            val userId = uploadRequest.userId
            val payloads = uploadRequest.payloads
        }catch (e: Error) {
            val errorMsg: String = if(e.message is String) e.message!! else e.toString()
            return responseService.isFailure(-1, errorMsg, uploadRequest)
        }


        return responseService.isSuccessful(null,null);
    }
}