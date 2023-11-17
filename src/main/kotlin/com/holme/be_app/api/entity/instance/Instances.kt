package com.holme.be_app.api.entity.instance

import kotlinx.serialization.Serializable

enum class InstanceType {
        REMAINING_SPACE, //* Padding for further use,
        LIGHT_BULB,
        CURTAIN,
        AC,
        REFRIGERATOR,
        WATER_DISPENSER,
        TELEVISION,
        SOUNDBAR,
        MASSAGE_CHAIR,
        AI_SPEAKER
}

interface Instance {}

//* Instance 1: Light bulb
@Serializable
data class LightBulb(
        var trigger: Boolean = false,
        var degree: Int = 0,
        var color: String = ""
): Instance {
        constructor(data: LightBulb) : this() {
                this.trigger = data.trigger
                this.degree = data.degree
                this.color = data.color
        }
}

//* Instance 2: Curtain
@Serializable
data class Curtain(
        var isHorizontal: Boolean = false,
        var isCenterMode: Boolean = false,
        var isLeftOrTop: Boolean = false,
        var degree: Int = 0
): Instance

//* Instance 3: AC
@Serializable
data class AC(
        var trigger: Boolean,
        var temperature: Int,
        var windDegree: Int
): Instance

//* Instance 4: Refrigerator
@Serializable
data class Refrigerator(
        var trigger: Boolean
): Instance

//* Instance 5: Water Dispenser
@Serializable
data class WaterDispenser(
        var triggerReminder: Boolean,
        var triggerWater: Boolean
): Instance

//* Instance 6: TV
@Serializable
data class Television(
        var trigger: Boolean
): Instance

//* Instance 7: Soundbar
@Serializable
data class SoundBar(
        var trigger: Boolean
): Instance

//* Instance 8: Massage Chair
@Serializable
data class MassageChair(
        var trigger: Boolean
): Instance

//* Instance 9: AI Speaker
@Serializable
data class AISpeaker(
        var trigger: Boolean = false,
        var replacementMsg: String = ""
): Instance
