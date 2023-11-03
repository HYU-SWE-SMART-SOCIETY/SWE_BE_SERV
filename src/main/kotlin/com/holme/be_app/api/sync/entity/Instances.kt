package com.holme.be_app.api.sync.entity

interface Instance {}

//* Instance 1: Light bulb
class LightBulb(
        trigger: Boolean = false,
        degree: Number = 0,
        color: String = ""
): Instance

//* Instance 2: Curtain
class Curtain(
        isHorizontal: Boolean = false,
        isVertical: Boolean = false,
        isLeftOrTop: Boolean = false,
        degree: Number = 0
): Instance

//* Instance 3: AC
class AC(
        trigger: Boolean = false,
        temperature: Number = 0,
        windDegree: Number = 0
): Instance

//* Instance 4: Refrigerator
class Refrigerator(
        trigger: Boolean = false
): Instance

//* Instance 5: Water Dispenser
class WaterDispenser(
        triggerReminder: Boolean = false,
        triggerWater: Boolean = false
): Instance

//* Instance 6: TV
class Television(
        trigger: Boolean = false
): Instance

//* Instance 7: Soundbar
class SoundBar(
        trigger: Boolean = false
): Instance

//* Instance 8: Massage Chair
class MassageChair(
        trigger: Boolean = false
): Instance

//* Instance 9: AI Speaker
class AISpeaker(
        trigger: Boolean = false,
        replacementMsg: Boolean = false
): Instance
