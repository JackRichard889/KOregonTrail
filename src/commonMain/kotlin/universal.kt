import com.soywiz.korio.file.std.resourcesVfs

object GameState {
    var date: Date = Date(4, 0)
    var pace: Int = 0
    var rations: Int = 0

    val registry: EnvironmentRegistry = EnvironmentRegistry().apply {
        val e1 = Environment(resourcesVfs["screens/assets/ground/grass.png"], "Grass", 3, 0.5)
        val e2 = Environment(resourcesVfs["screens/assets/ground/desert.png"], "Desert", 5, 0.8)
        val e3 = Environment(resourcesVfs["screens/assets/ground/snow.png"], "Snow", 5, 0.2)
        val e4 = Environment(resourcesVfs["screens/assets/ground/grass.png"], "Grass", 10, 0.5)
        this.add_environment(e1)
        this.add_environment(e2)
        this.add_environment(e3)
        this.add_environment(e4)

        this.add_landmark(Landmarks.Landmarks.KANSAS_RIVER, 6, Landmarks.Types.RIVER)
        this.add_landmark(Landmarks.Landmarks.FORT_KEARNEY, 13, Landmarks.Types.FORT)
        this.add_landmark(Landmarks.Landmarks.CHIMNEY_ROCK, 22, Landmarks.Types.TEXTURED, "screens/assets/location/chimney_rock.png")
        this.add_landmark(Landmarks.Landmarks.FORT_LARAMIE, 30, Landmarks.Types.FORT)
        this.add_landmark(Landmarks.Landmarks.INDEPENDENCE_ROCK, 37, Landmarks.Types.FORT)
        this.add_landmark(Landmarks.Landmarks.SOUTH_PASS, 44, Landmarks.Types.FORT)
        this.add_landmark(Landmarks.Landmarks.SODA_SPRINGS, 52, Landmarks.Types.RIVER)
        this.add_landmark(Landmarks.Landmarks.FORT_BRIDGER, 62, Landmarks.Types.FORT)
        this.add_landmark(Landmarks.Landmarks.FORT_HALL, 71, Landmarks.Types.RIVER)
        this.add_landmark(Landmarks.Landmarks.FORT_BOISE, 83, Landmarks.Types.FORT)
        this.add_landmark(Landmarks.Landmarks.BLUE_MOUNTAINS, 92, Landmarks.Types.RIVER)
        this.add_landmark(Landmarks.Landmarks.FORT_WALLA_WALLA, 100, Landmarks.Types.FORT)
        this.add_landmark(Landmarks.Landmarks.THE_DALLES, 112, Landmarks.Types.RIVER)
    }

    var trailLength: Int = registry.get_length()
    var environments: List<Environment> = registry.get_array()
    var traveled: Double = 0.0
    val alerts: MutableList<String> = mutableListOf()
    val wagon: Wagon = Wagon()

    val extras: MutableMap<String, Any> = mutableMapOf()
}