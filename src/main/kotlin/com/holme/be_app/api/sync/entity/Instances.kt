package com.holme.be_app.api.sync.entity

import kotlinx.serialization.Serializable

interface Instance {}

//* Instance 1: Light bulb
@Serializable
data class LightBulb(
        var trigger: Boolean = false,
        var degree: Int = 0,
        var color: String = ""
): Instance

//* Instance 2: Curtain
@Serializable
data class Curtain(
        var isHorizontal: Boolean = false,
        var isVertical: Boolean = false,
        var isLeftOrTop: Boolean = false,
        var degree: Int = 0
): Instance

//* Instance 3: AC
class AC(
        var trigger: Boolean,
        var temperature: Number,
        var windDegree: Number
): Instance

//* Instance 4: Refrigerator
class Refrigerator(
        var trigger: Boolean
): Instance

//* Instance 5: Water Dispenser
class WaterDispenser(
        var triggerReminder: Boolean,
        var triggerWater: Boolean
): Instance

//* Instance 6: TV
class Television(
        var trigger: Boolean
): Instance

//* Instance 7: Soundbar
class SoundBar(
        var trigger: Boolean
): Instance

//* Instance 8: Massage Chair
class MassageChair(
        var trigger: Boolean
): Instance

//* Instance 9: AI Speaker
class AISpeaker(
        var trigger: Boolean,
        var replacementMsg: Boolean
): Instance
