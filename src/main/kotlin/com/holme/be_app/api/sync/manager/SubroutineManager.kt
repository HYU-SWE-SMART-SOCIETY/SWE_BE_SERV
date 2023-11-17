package com.holme.be_app.api.sync.manager

import com.holme.be_app.api.entity.instance.InstanceType
import com.holme.be_app.api.sync.entity.ConnectedDevice
import com.holme.be_app.api.sync.entity.Substitute
import org.springframework.stereotype.Component

//TODO: Hard-coded, need to be refactored after demo.

class SubroutineManager(private val connectedList: List<ConnectedDevice>) {

    fun checkSubroutines(curInstId: Int): Substitute? {
        //* Demo 1: Upgradable Soundbar
        if(curInstId == 9 && connectedList[InstanceType.SOUNDBAR.ordinal].isExist){
            return Substitute(
                curInstId,
                "SOUNDBAR_UPGRADABLE",
                true
            )
        }
        //* Demo 2: Pet Feeder <- Substitute to Water Dispenser
        if(curInstId == 10 && connectedList[InstanceType.WATER_DISPENSER.ordinal].isExist) {
            return Substitute(
                curInstId,
                "PET_FEEDER_SUB_TO_WATER_DISPENSER",
                false
            )
        }

        return null
    }
}