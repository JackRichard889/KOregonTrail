package me.jackrichard.oregontrail.data

import kotlin.random.Random

class Person(override val name: String, val isMainPerson: Boolean = false) : Entity() {
    private var diseases = mutableMapOf<Diseases, Int>()
    var occupation: Occupation = Occupation.NONE

    override fun onUpdate() {
        val food_rations: Map<Int, Int> = mapOf(0 to 3, 1 to 2, 2 to 1)

        if ((GameState.wagon.inventory.food - food_rations[GameState.rations]!!) < 0) {
            health -= 1
        } else {
            GameState.wagon.inventory.food -= food_rations[GameState.rations] ?: 0
            if (health < 10) {
                health += 2
            }
        }

        diseases.toMap().keys.forEach { disease ->
            diseases[disease] = diseases[disease]!! - 1
            if (diseases[disease]!! < 1) {
                GameState.alerts.add(name + " is cured of " + disease.name + "!")
                diseases.remove(disease)
            } else if (disease.fatal) {
                health -= 2
            }
            if (disease.instant) {
                health = 0
            }
        }

        if (Random.nextInt(0, 10) == 1) {
            Diseases.affecting(Person::class)
                .run { randomWithWeights(map(Diseases::probability)) }
                .also {
                    if (it.fatal) {
                        health = 0
                    } else {
                        GameState.alerts.add("$name has $it!")
                        diseases[it] = 3
                    }
                }
        }
    }

    override fun onDead() { GameState.alerts.add("$name is dead.") }
}