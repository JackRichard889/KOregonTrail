package me.jackrichard.oregontrail.data

abstract class Entity {
    abstract val name: String
    open var health: Int = 10
        set(value) { if (value < 1) { onDead() }; field = value }
    open val alive: Boolean
        get() = health > 0

    abstract fun onUpdate()
    abstract fun onDead()
}