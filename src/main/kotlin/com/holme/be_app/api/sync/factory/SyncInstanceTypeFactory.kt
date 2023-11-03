package com.holme.be_app.api.sync.factory

import com.holme.be_app.api.sync.entity.*
import com.holme.be_app.entity.*

class Instances {
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
class SyncInstanceTypeFactory {

    private val instances: Instances = Instances()
    fun generateInstanceClass(type:Number): Instance {
        return instances.instanceMap[type]!!
    }
}