package com.holme.be_app.api.sync.factory

import com.holme.be_app.api.sync.entity.*
import org.springframework.stereotype.Component

private class FactoryInstances {
    val instanceMap = mapOf<Number, Instance>(
            1 to LightBulb(),
            2 to Curtain(),
            3 to AC(),
            4 to Refrigerator(),
            5 to WaterDispenser(),
            6 to Television(),
            7 to SoundBar(),
            8 to MassageChair(),
            9 to AISpeaker()
    )
}

@Component
class SyncInstanceTypeFactory {

    private val factoryInstances: FactoryInstances = FactoryInstances()
    fun generateInstanceClass(type:Number): Instance {
        return factoryInstances.instanceMap[type]!!
    }
}