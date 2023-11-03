package com.holme.be_app.api.sync.controller

import com.holme.be_app.api.sync.entity.Instance
import com.holme.be_app.api.sync.entity.SingleSyncRequest
import com.holme.be_app.api.sync.entity.SyncRequest
import com.holme.be_app.api.sync.entity.SyncResponse
import com.holme.be_app.api.sync.factory.SyncInstanceTypeFactory
import com.holme.be_app.api.sync.service.SyncRequestService
import com.holme.be_app.entity.response.SingleResponse
import com.holme.be_app.entity.response.SingleResponseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/sync")
class SyncController(
    @Autowired private val syncRequestService: SyncRequestService<Instance>,
    @Autowired private val syncInstanceTypeFactory: SyncInstanceTypeFactory,
    @Autowired private val responseService: SingleResponseService<SyncResponse>
) {
    @PostMapping("/request")
    fun handleSyncRequest(@RequestBody syncRequest: SyncRequest<Instance>): SingleResponse<SyncResponse> {
        try{
            val requestPayloads = syncRequest.payloads
            for (request: SingleSyncRequest<Instance> in requestPayloads) {
                //* Handle & Send every request received
                val type = request.instanceType
                val instance = syncInstanceTypeFactory.generateInstanceClass(type)
                syncRequestService.sendSyncRequest(type, instance)
            }
            // TODO: Add Response Type
        }catch (e: Error) {
            val message: String = if(e.message is String) e.message!! else e.toString()
            return responseService.isFailure(-1, message)
        }
        return responseService.isSuccessful()
    }
}