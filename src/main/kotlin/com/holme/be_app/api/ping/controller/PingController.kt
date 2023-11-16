package com.holme.be_app.api.ping.controller

import com.holme.be_app.api.entity.instance.InstanceType
import com.holme.be_app.api.entity.response.SingleResponse
import com.holme.be_app.api.entity.response.SingleResponseService
import com.holme.be_app.api.ping.entity.PingResponse
import com.holme.be_app.api.ping.entity.SinglePingRequest
import com.holme.be_app.api.ping.manager.InstanceMapManager
import com.holme.be_app.api.ping.service.PingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/ping")
class PingController (
    @Autowired private val instanceMapManager: InstanceMapManager,
    @Autowired private val pingService: PingService,
    @Autowired private val responseService: SingleResponseService<PingResponse>
) {

    @PostMapping("/")
    fun handlePingRequest(@RequestBody singlePingRequest: SinglePingRequest): SingleResponse<PingResponse> {
        return try{
            val userId = singlePingRequest.payload.userId
            val instanceList = instanceMapManager.initializeInstanceList()

            val connectedList = pingService.sendPingRequest(userId) ?: throw Error("No Connected List")

            for (connected in connectedList.Result) {
                if(connected.IsExist) {
                    val instName: InstanceType = InstanceType.entries[connected.InstanceId]
                    instanceMapManager.markInstanceAsExists(instanceList,instName)
                }
            }

            responseService.isSuccessful("PING RESULT", connectedList)
        }catch (e: Error) {
            val message: String = if(e.message is String) e.message!! else e.toString()
            responseService.isFailure(-1, message, null)
        }
    }
}