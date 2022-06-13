import kotlin.random.Random

class Disease(val name: String, val probability: Double, val affects: Affects, val fatal: Boolean, val instant: Boolean) {
    enum class Affects {
        PERSON,
        OXEN
    }

    val affectsList: Map<Affects, MutableList<Disease>> = mapOf(Affects.PERSON to mutableListOf(), Affects.OXEN to mutableListOf())

    init {
        affectsList[affects]?.add(this)
    }
}

enum class DiseaseList(val disease: Disease) {
    CHOLERA(Disease("Cholera", 0.1, Disease.Affects.PERSON, fatal = true, instant = false)),
    DIPHTHERIA(Disease("Diphtheria", 0.1, Disease.Affects.PERSON, fatal = true, instant = false)),
    DYSENTERY(Disease("Dysentery", 0.2, Disease.Affects.PERSON, fatal = true, instant = false)),
    MEASLES(Disease("Measles", 0.1, Disease.Affects.PERSON, fatal = true, instant = false)),
    TYPHOID(Disease("Typhoid Fever", 0.1, Disease.Affects.PERSON, fatal = true, instant = false)),
    SNAKEBITE(Disease("Snakebite", 0.1, Disease.Affects.PERSON, fatal = true, instant = false)),
    DROWNING(Disease("Drowned", 0.05, Disease.Affects.PERSON, fatal = true, instant = true)),
    GUNSHOT(Disease("Gunshot Wound", 0.1, Disease.Affects.PERSON, fatal = true, instant = false)),
    EXHAUSTION(Disease("Exhaustion", 0.1, Disease.Affects.PERSON, fatal = true, instant = false)),
    BROKEN_ARM(Disease("Broken Arm", 0.1, Disease.Affects.PERSON, fatal = false, instant = false)),
    BROKEN_LEG(Disease("Broken Leg", 0.1, Disease.Affects.PERSON, fatal = false, instant = false)),
    HEALTHY(Disease("Healthy", 1.0, Disease.Affects.PERSON, fatal = false, instant = false)),
    HEALTHY_OXEN(Disease("Healthy", 1.0, Disease.Affects.OXEN, fatal = false, instant = false)),
    INJURY(Disease("injured", 0.1, Disease.Affects.OXEN, fatal = true, instant = false)),
    ROBBERY(Disease("stolen", 0.1, Disease.Affects.OXEN, fatal = true, instant = false))
}

class Oxen(val name: String, var health: Int = 10, var alive: Boolean = true) {
    fun update(data: MutableMap<String, Any>) : MutableMap<String, Any> {
        if (Random.nextInt(0, 5) == 1) {
            val diseaseNew = choices(
                Disease.affectsList[Disease.Affects.OXEN], [
                    d.probability
                for d in Disease.affectsList[Disease.Affects.OXEN]
            ], k = 1)[0]

            if (diseaseNew.name.upper() == "STOLEN") {
                alive = false
            } else if (diseaseNew . name . upper () == "INJURED") {
                health -= 4
            }
            if (diseaseNew.name.upper() != "HEALTHY") {
                data["alerts"].add("An ox has been " + diseaseNew.name + "!")
            }
        }

        if (health < 1) {
            alive = false
            data["alerts"].add("One of your oxen has died.")
        }

        return data
    }
}

class Person(val name: String, var health: Int = 10, val isMainPerson: Boolean = false, val diseases: MutableList<Disease>, var alive: Boolean = true, val occupation: String) {
    fun update(data: MutableMap<String, Any>) : MutableMap<String, Any> {
        val food_rations: Map<Int, Int> = mapOf(0 to 3, 1 to 2, 2 to 1)

        if ((data["wagon"].inventory["food"] - food_rations[data["rations"]]) < 0) {
            health -= 1
        } else {
            data["wagon"].inventory["food"] -= food_rations[data["rations"]]
            if (health < 10) {
                health += 2
            }
        }

        diseases.toList().forEach { disease ->
            diseases[disease] -= 1
            if (diseases[disease] < 1) {
                data["alerts"].add(name + " is cured of " + disease.name + "!")
                diseases.pop(disease)
            } else if (disease.fatal) {
                health -= 2
            }
            if (disease.instant) {
                health = 0
            }
        }

        if (Random.nextInt(0, 11) == 1) {
            val diseaseNew = choices(
                Disease.affectsList[Disease.Affects.PERSON], [
                    d.probability
                for d in Disease.affectsList[Disease.Affects.PERSON]
            ], k = 1)[0]

            if (diseaseNew.name.upper() != DiseaseList.HEALTHY.name) {
                data["alerts"].add(name + " has " + diseaseNew.name + "!")
                diseases[diseaseNew] = 3
            }
        }

        if (health < 1) {
            alive = false
            data["alerts"].add("$name is dead.")
        }

        return data
    }
}