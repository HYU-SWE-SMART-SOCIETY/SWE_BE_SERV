package com.holme.be_app.api.sync.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.holme.be_app.api.entity.instance.Instance
import com.holme.be_app.api.entity.instance.InstanceType
import com.holme.be_app.api.sync.entity.*
import com.holme.be_app.api.sync.factory.SyncInstanceTypeFactory
import com.holme.be_app.api.sync.service.SyncRequestService
import com.holme.be_app.api.entity.response.SingleResponse
import com.holme.be_app.api.entity.response.SingleResponseService
import com.holme.be_app.api.report.service.ReportService
import com.holme.be_app.api.sync.manager.SubroutineManager
import com.holme.be_app.api.sync.service.sendReportService
import com.holme.be_app.entity.ReportType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/sync")
class SyncController(
    @Autowired private val reportService: ReportService,
    @Autowired private val sendReportService: sendReportService,
    @Autowired private val syncRequestService: SyncRequestService<in Instance>,
    @Autowired private val syncInstanceTypeFactory: SyncInstanceTypeFactory,
    @Autowired private val responseService: SingleResponseService<SyncResponse>
) {
    @PostMapping("/request")
    fun handleSyncRequest(@RequestBody syncRequest: SyncRequest<in Instance>, @RequestParam type: String?): SingleResponse<SyncResponse> {
        val requestQueue: MutableList<SendSyncRequest> = mutableListOf<SendSyncRequest>()
        val substitutionQueue: MutableList<Substitute> = mutableListOf()
        val instanceNameQueue: MutableList<InstanceType> = mutableListOf()
        val syncType: String = type ?: "sync" //* Default Value: Sync
        requestQueue.clear()
        try{
            val user = syncRequest.user
            val requestPayloads = syncRequest.payloads
            val connectedDevices = syncRequest.connectedDevices //* Will be used for subroutines

            val subroutineManager = SubroutineManager(connectedDevices)

            for (request: SingleSyncRequest<in Instance> in requestPayloads) {
                //* Handle & Send every request received
                //TODO: Need to add mechanism for subroutine.

                val instanceType = request.instanceType
                val subResp = subroutineManager.checkSubroutines(instanceType)
                if(subResp != null){
                    //* Subroutine Exists.
                    substitutionQueue.add(subResp)
                }
                //if(subResp!= null && !subResp.isUpgrade) continue //* No device in target area, shouldn't send the request.

                instanceNameQueue.add(InstanceType.entries[instanceType]) //* Add instance type in name queue, it will later be used for report.
                val data = request.payload!!
                val instance = syncInstanceTypeFactory.generateInstanceClass(instanceType,data)
                    ?: //* Return Value is null == Something is wrong
                    throw Error("Error while serializing the data")

                requestQueue.add(SendSyncRequest(
                    instanceType,
                    ObjectMapper().writeValueAsString(instance)
                ))
            }
            val resp: SyncResponse = syncRequestService.sendSyncRequest(user,requestQueue, substitutionQueue)

            val reportType: ReportType = SyncType().returnSyncType(syncType)
            val instListMsg: String = "SUCCESSFUL: $instanceNameQueue"

            if(!resp.ok) throw Error("Error from HIVEMIND: ${resp.message}") //* Error from HIVEMIND

            if(requestPayloads.size <= 1 && (requestPayloads[0].instanceType == 9 || requestPayloads[0].instanceType == 10)){
                //Demo: For the fast response.
                if(requestPayloads[0].instanceType == 9){
                    //* TODO Change hard-coded userId
                    reportService.generateReport(2, ReportType.SYNC, "성공적으로 연결되었습니다. \n - AI 스피커")
                }else{
                    //* TODO Change hard-coded userId
                    reportService.generateReport(2, ReportType.REPLACEMENT, "애완용 급수기가 존재하지 않습니다. 대체 알고리즘이 동작합니다.")
                }
            }
            else if(!sendReportService.sendReportRequest(user, reportType, instListMsg)){
                throw Error("Error! Something gone wrong in report service.")
            }

            return responseService.isSuccessful(null, resp)
        }catch (e: Error) {
            val message: String = if(e.message is String) e.message!! else e.toString()
            return responseService.isFailure(-1, message, null)
        }
    }
}