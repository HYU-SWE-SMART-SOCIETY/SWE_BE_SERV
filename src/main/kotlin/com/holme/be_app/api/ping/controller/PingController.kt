package com.holme.be_app.api.ping.controller

import com.holme.be_app.api.entity.response.SingleResponse
import com.holme.be_app.api.entity.response.SingleResponseService
import com.holme.be_app.api.ping.entity.PingResponse
import com.holme.be_app.api.ping.entity.SinglePingRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/ping")
class PingController (
    @Autowired private val responseService: SingleResponseService<PingResponse>
) {

    @PostMapping("/")
    fun handlePingRequest(@RequestBody singlePingRequest: SinglePingRequest): SingleResponse<PingResponse> {
        return try{
            val userId = singlePingRequest.payload.userId

            //TODO

            responseService.isSuccessful("", null) //TODO
        }catch (e: Error) {
            val message: String = if(e.message is String) e.message!! else e.toString()
            responseService.isFailure(-1, message, null)
        }
    }
}