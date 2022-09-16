package me.jackrichard.oregontrail.data

class Wagon(var party: List<Entity> = listOf()) {
    class Inventory(party: List<Person>) {
        var food = 0
        var cash = party.first(Person::isMainPerson).occupation.money
        var clothing = 0
        var ammunition = 0
        var axel = 0
        var wheel = 0
        var tongue = 0
    }

    enum class DamagedPart {
        NONE,
        AXEL,
        WHEEL,
        TONGUE
    }

    val inventory = Inventory(party)

    fun update() = party.forEach(Entity::onUpdate)

    val isPartyDead: Boolean
        get() = party.filterIsInstance<Person>().first { it.isMainPerson }.alive
}