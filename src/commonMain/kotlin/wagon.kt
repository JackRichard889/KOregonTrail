public class Wagon(var party: List<Person> = listOf()) {
    class Inventory(party: List<Person>) {
        var food = 0
        var cash = money_for_profession(party.firstOrNull() { it.isMainPerson }?.occupation ?: 0)
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

    val oxen = mutableListOf<Oxen>()

    fun update_party() {
        party.forEach {
            if (it.alive) {
                it.update()
            }
        }
    }

    fun update_oxen() {
        oxen.forEach {
            if (it.alive) {
                it.update()
            } else {
                oxen.remove(it)
            }
        }
    }

    fun update() {
        update_party()
        update_oxen()
    }

    fun is_party_dead() = party.first { it.isMainPerson }.alive
}