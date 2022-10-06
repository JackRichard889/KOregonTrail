package me.jackrichard.oregontrail.data

import com.soywiz.korio.file.std.resourcesVfs

object GameState {
    enum class Pace(val distance: Double) {
        STEADY(10.0),
        STRENUOUS(5.0),
        GRUELING(2.5)
    }

    enum class Rations(val identifier: String, val description: String, val amount: Int) {
        FILLING("filling", "meals are large and generous", 4),
        MEAGER("meager", "meals are small, but adequate", 2),
        BARE_BONES("bare bones", "meals are very small; everyone stays hungry", 1)
    }

    var date: Date = Date(4, 0)
    var pace: Pace = Pace.STEADY
    var rations: Rations = Rations.FILLING

    val registry: EnvironmentRegistry = EnvironmentRegistry().apply {
        val e1 = Environment(resourcesVfs["me/jackrichard/oregontrail/scenes/assets/ground/grass.png"], "Grass", 3, 0.5)
        val e2 = Environment(resourcesVfs["me/jackrichard/oregontrail/scenes/assets/ground/desert.png"], "Desert", 5, 0.8)
        val e3 = Environment(resourcesVfs["me/jackrichard/oregontrail/scenes/assets/ground/snow.png"], "Snow", 5, 0.2)
        val e4 = Environment(resourcesVfs["me/jackrichard/oregontrail/scenes/assets/ground/grass.png"], "Grass", 10, 0.5)
        addEnvironment(e1)
        addEnvironment(e2)
        addEnvironment(e3)
        addEnvironment(e4)

        addLandmark(Landmarks.Landmarks.KANSAS_RIVER, 6, Landmarks.Types.RIVER)
        addLandmark(Landmarks.Landmarks.FORT_KEARNEY, 13, Landmarks.Types.FORT)
        addLandmark(Landmarks.Landmarks.CHIMNEY_ROCK, 22, Landmarks.Types.TEXTURED, "me/jackrichard/oregontrail/scenes/assets/location/chimney_rock.png")
        addLandmark(Landmarks.Landmarks.FORT_LARAMIE, 30, Landmarks.Types.FORT)
        addLandmark(Landmarks.Landmarks.INDEPENDENCE_ROCK, 37, Landmarks.Types.FORT)
        addLandmark(Landmarks.Landmarks.SOUTH_PASS, 44, Landmarks.Types.FORT)
        addLandmark(Landmarks.Landmarks.SODA_SPRINGS, 52, Landmarks.Types.RIVER)
        addLandmark(Landmarks.Landmarks.FORT_BRIDGER, 62, Landmarks.Types.FORT)
        addLandmark(Landmarks.Landmarks.FORT_HALL, 71, Landmarks.Types.RIVER)
        addLandmark(Landmarks.Landmarks.FORT_BOISE, 83, Landmarks.Types.FORT)
        addLandmark(Landmarks.Landmarks.BLUE_MOUNTAINS, 92, Landmarks.Types.RIVER)
        addLandmark(Landmarks.Landmarks.FORT_WALLA_WALLA, 100, Landmarks.Types.FORT)
        addLandmark(Landmarks.Landmarks.THE_DALLES, 112, Landmarks.Types.RIVER)
    }

    var trailLength: Int = registry.length()
    var environments: List<Environment> = registry.environments
    var traveled: Double = 0.0
    val alerts: MutableList<String> = mutableListOf()
    val wagon: Wagon = Wagon()

    val extras: MutableMap<String, Any> = mutableMapOf()
}