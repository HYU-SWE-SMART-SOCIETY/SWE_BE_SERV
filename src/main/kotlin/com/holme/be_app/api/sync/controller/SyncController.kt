package com.holme.be_app.api.sync.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.holme.be_app.api.entity.instance.Instance
import com.holme.be_app.api.sync.entity.*
import com.holme.be_app.api.sync.factory.SyncInstanceTypeFactory
import com.holme.be_app.api.sync.service.SyncRequestService
import com.holme.be_app.api.entity.response.SingleResponse
import com.holme.be_app.api.entity.response.SingleResponseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/sync")
class SyncController(
    @Autowired private val syncRequestService: SyncRequestService<in Instance>,
    @Autowired private val syncInstanceTypeFactory: SyncInstanceTypeFactory,
    @Autowired private val responseService: SingleResponseService<SyncResponse>
) {
    @PostMapping("/request")
    fun handleSyncRequest(@RequestBody syncRequest: SyncRequest<in Instance>): SingleResponse<SyncResponse> {
        val requestQueue: MutableList<SendSyncRequest> = mutableListOf<SendSyncRequest>()
        requestQueue.clear()
        try{
            val user = syncRequest.user
            val requestPayloads = syncRequest.payloads

            for (request: SingleSyncRequest<in Instance> in requestPayloads) {
                //* Handle & Send every request received
                val type = request.instanceType
                val data = request.payload!!
                val instance = syncInstanceTypeFactory.generateInstanceClass(type,data)
                    ?: //* Return Value is null == Something is wrong
                    throw Error("Error while serializing the data")

                requestQueue.add(SendSyncRequest(
                    type,
                    ObjectMapper().writeValueAsString(instance)
                ))
            }
            val resp: SyncResponse = syncRequestService.sendSyncRequest(user,requestQueue)

            if(!resp.ok) throw Error("Error from HIVEMIND: ${resp.message}") //* Error from HIVEMIND
        }catch (e: Error) {
            val message: String = if(e.message is String) e.message!! else e.toString()
            return responseService.isFailure(-1, message, null)
        }
        return responseService.isSuccessful(null, null)
    }
}