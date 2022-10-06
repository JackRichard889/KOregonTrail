package me.jackrichard.oregontrail.data

import kotlin.reflect.KClass

enum class Diseases(private val identifier: String, val probability: Double, val affects: KClass<out Entity>, val fatal: Boolean = false, val instant: Boolean = false) {
    CHOLERA("Cholera", 0.1, Person::class, fatal = true),
    DIPHTHERIA("Diphtheria", 0.1, Person::class, fatal = true),
    DYSENTERY("Dysentery", 0.2, Person::class, fatal = true),
    MEASLES("Measles", 0.1, Person::class, fatal = true),
    TYPHOID("Typhoid Fever", 0.1, Person::class, fatal = true),
    SNAKEBITE("Snakebite", 0.1, Person::class, fatal = true),
    DROWNING("Drowned", 0.05, Person::class, fatal = true, instant = true),
    GUNSHOT("Gunshot Wound", 0.1, Person::class, fatal = true),
    EXHAUSTION("Exhaustion", 0.1, Person::class, fatal = true),
    BROKEN_ARM("Broken Arm", 0.1, Person::class),
    BROKEN_LEG("Broken Leg", 0.1, Person::class),
    HEALTHY("Healthy", 1.0, Person::class),
    HEALTHY_OXEN("Healthy", 1.0, Oxen::class),
    INJURY("injured", 0.1, Oxen::class, fatal = true),
    ROBBERY("stolen", 0.1, Oxen::class, fatal = true);

    override fun toString() = identifier.lowercase()

    companion object {
        fun affecting(type: KClass<out Entity>) = values().filter { it.affects == type }
    }
}