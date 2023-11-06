package com.holme.be_app.api.sync.factory

import com.fasterxml.jackson.databind.ObjectMapper
import com.holme.be_app.api.sync.entity.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
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
            val instance: T = Json.decodeFromString(data)
            instance
        } catch (e: Exception) {
            println(e.message)
            null
        }
    }

    private fun fillInstance(instance: Instance, data: Any): Instance? {
        val jsonStr: String = ObjectMapper().writeValueAsString(data)

        return when(instance) {
            is LightBulb -> decodeInstance<LightBulb>(jsonStr)
            is Curtain -> decodeInstance<Curtain>(jsonStr)
            is AC -> decodeInstance<AC>(jsonStr)
            is Refrigerator -> decodeInstance<Refrigerator>(jsonStr)
            is WaterDispenser -> decodeInstance<WaterDispenser>(jsonStr)
            is Television -> decodeInstance<Television>(jsonStr)
            is SoundBar -> decodeInstance<SoundBar>(jsonStr)
            is MassageChair -> decodeInstance<MassageChair>(jsonStr)
            is AISpeaker -> decodeInstance<AISpeaker>(jsonStr)
            else -> return null
        }
    }

    inline fun  <reified T: Instance>createInstance(type: Number, data: Any): T?{
        val instClass = instanceMap[type]!!
        return try{
            val constructor = instClass.getConstructor()
            var instance = constructor.newInstance()
            instance = fillInstance(instance,data) ?: throw Error("Error while creating instance")

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
    fun generateInstanceClass(type:Number, data: Any): Instance? {
        return factoryInstances.createInstance(type, data)
    }
}