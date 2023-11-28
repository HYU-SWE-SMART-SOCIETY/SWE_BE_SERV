package com.holme.be_app.api.ping.service

import com.holme.be_app.api.ping.entity.PingRequest
import com.holme.be_app.api.ping.entity.PingResult
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class PingService {
    fun sendPingRequest(userId: Int): PingResult? {
        try{
            val ping = { userId }
            val restTemplate = RestTemplate()
            val header = HttpHeaders()

            header.contentType = MediaType.APPLICATION_JSON
            val request = HttpEntity(ping, header)

            val resp: ResponseEntity<String> = restTemplate.postForEntity(
                "http://localhost:10000/ping",
                request,
                String::class.java
            )

            if(!resp.statusCode.is2xxSuccessful) throw Error("Response Not Successful")

            val stringified = resp.body ?: throw Error("No Body")
            return Json.decodeFromString<PingResult>(stringified)

        }catch (e:Error){
            println("Error! " + e.message)
            return null
        }
    }
}