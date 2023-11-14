package com.holme.be_app.api.ping.manager

import com.holme.be_app.api.entity.instance.InstanceType

class InstanceMapManager {
    fun initializeInstanceList(): Map<InstanceType, Boolean> {
        return InstanceType.entries.associateWith { false }
    }

    fun markInstanceAsExists(instList: Map<InstanceType, Boolean>, instName: InstanceType): Map<InstanceType, Boolean> {
        return instList.toMutableMap().apply { this[instName] = true }.toMap()
    }
}