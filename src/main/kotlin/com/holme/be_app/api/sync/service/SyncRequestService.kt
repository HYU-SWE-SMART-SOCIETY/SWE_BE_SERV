package com.holme.be_app.api.sync.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.holme.be_app.api.sync.entity.SendHBRequest
import com.holme.be_app.api.sync.entity.SendSyncRequest
import com.holme.be_app.api.sync.entity.SyncResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForObject

@Service
class SyncRequestService <T> {

    fun sendSyncRequest(
        user: String,
        requests: MutableList<SendSyncRequest>
    ): SyncResponse {
        //* TODO: Handle Data
        val sendHBRequest = SendHBRequest(user, requests)
        val restTemplate = RestTemplate()
        val header = HttpHeaders()
        //* Send Request
        return try{
            header.contentType = MediaType.APPLICATION_JSON
            val request = HttpEntity(sendHBRequest, header)
            // POST request
            val resp: ResponseEntity<String> = restTemplate.postForEntity(
                "http://localhost:10000/request",
                request,
                String::class.java
            )

            if(resp.statusCode.is2xxSuccessful){
                SyncResponse(true, null)
            }else{
                throw Error("StatusCode: ${resp.statusCode}")
            }

        }catch (e: Error){
            println(e.message)
            SyncResponse(
                false, e.message
            )
        }
    }
}