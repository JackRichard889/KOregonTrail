package me.jackrichard.oregontrail.data

import com.soywiz.korma.random.randomWithWeights
import kotlin.random.Random

class Oxen : Entity() {
    override val name: String = "Oxen"
    override var health: Int = 10
        set(value) { if (value < 1) { onDead() }; field = value }
    override val alive: Boolean
        get() = health > 0

    override fun onUpdate() {
        if (Random.nextInt(0, 5) == 1) {
            when (Diseases.affecting(Oxen::class).run { randomWithWeights(map(Diseases::probability)) }) {
                Diseases.ROBBERY -> { health = 0; GameState.alerts.add("An ox has been stolen!") }
                Diseases.INJURY -> { health -= 4; GameState.alerts.add("An ox has been injured!") }
                else -> if (health < 10) { health++ }
            }
        }
    }

    override fun onDead() { GameState.alerts.add("One of your oxen has died.") }
}