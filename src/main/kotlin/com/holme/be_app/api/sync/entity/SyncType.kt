package com.holme.be_app.api.sync.entity

import com.holme.be_app.entity.ReportType
import org.springframework.stereotype.Service


@Service
class SyncType {
    fun returnSyncType(type: String): ReportType {
        return when (type){
            "sync" -> ReportType.SYNC
            "upgr" -> ReportType.UPGRADE
            "repl" -> ReportType.REPLACEMENT
            else -> ReportType.SYNC//* DEFAULT
        }
    }
}