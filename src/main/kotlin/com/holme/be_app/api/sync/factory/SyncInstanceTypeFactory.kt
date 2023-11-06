package com.holme.be_app.api.sync.factory

import com.holme.be_app.api.sync.entity.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Component

private class FactoryInstances {
    private val instanceMap = mapOf<Number, Class<out Instance>>(
            1 to LightBulb::class.java,
            2 to Curtain::class.java,
            3 to AC::class.java,
            4 to Refrigerator::class.java,
            5 to WaterDispenser::class.java,
            6 to Television::class.java,
            7 to SoundBar::class.java,
            8 to MassageChair::class.java,
            9 to AISpeaker::class.java
    )
    inline fun <reified T : Instance> decodeInstance(data: String): T? { //Decode the data into dedicated string
        return try {
            Json.decodeFromString(data)
        } catch (e: Exception) {
            null
        }
    }
    private fun fillInstance(instance: Instance, data: String){
        when(instance) {
            is LightBulb -> decodeInstance<LightBulb>(data)
            is Curtain -> decodeInstance<Curtain>(data)
            is AC -> decodeInstance<AC>(data)
            is Refrigerator -> decodeInstance<Refrigerator>(data)
            is WaterDispenser -> decodeInstance<WaterDispenser>(data)
            is Television -> decodeInstance<Television>(data)
            is SoundBar -> decodeInstance<SoundBar>(data)
            is MassageChair -> decodeInstance<MassageChair>(data)
            is AISpeaker -> decodeInstance<AISpeaker>(data)
        }
    }

    inline fun  <reified T: Instance>createInstance(type: Number, data: String): T?{
        val instClass = instanceMap[type]!!
        return try{
            val constructor = instClass.getConstructor()
            val instance = constructor.newInstance()
            fillInstance(instance,data)
            instance as T
        }catch (e: Error){
            println(e.message)
            null
        }
    }
}

@Component
class SyncInstanceTypeFactory {

    private val factoryInstances: FactoryInstances = FactoryInstances()
    fun generateInstanceClass(type:Number, data: String): Instance? {
        return factoryInstances.createInstance(type, data)
    }
}