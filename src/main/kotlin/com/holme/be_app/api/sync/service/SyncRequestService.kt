package com.holme.be_app.api.sync.service

import org.springframework.stereotype.Service

@Service
class SyncRequestService <T> {

    fun sendSyncRequest(
        instanceType: Int,
        payload: T,
    ) {
        //* TODO: Handle Data
        println(payload.toString())
    }
}