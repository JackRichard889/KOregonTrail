import com.soywiz.korio.file.std.resourcesVfs

public class Landmarks {
    enum class Types {
        RIVER,
        FORT,
        TEXTURED
    }

    val textures = mapOf(
        Types.RIVER to util.imgscale(resourcesVfs["location/river.png"], 2),
        Types.FORT to util.imgscale(resourcesVfs["location/fort.png"], 2)
    )

    enum class Landmarks {
        INDEPENDENCE,
        FORT_KEARNEY,
        CHIMNEY_ROCK,
        FORT_LARAMIE,
        INDEPENDENCE_ROCK,
        SOUTH_PASS,
        SODA_SPRINGS,
        FORT_BRIDGER,
        FORT_HALL,
        FORT_BOISE,
        BLUE_MOUNTAINS,
        FORT_WALLA_WALLA,
        THE_DALLES,
        KANSAS_RIVER
    }

    val names = mapOf(
        Landmarks.INDEPENDENCE to "Independence, Missouri",
        Landmarks.FORT_KEARNEY to "Fort Kearney",
        Landmarks.CHIMNEY_ROCK to "Chimney Rock",
        Landmarks.FORT_LARAMIE to "Fort Laramie",
        Landmarks.INDEPENDENCE_ROCK to "Independence Rock",
        Landmarks.SOUTH_PASS to "South Pass",
        Landmarks.SODA_SPRINGS to "Soda Springs",
        Landmarks.FORT_BRIDGER to "Fort Bridger",
        Landmarks.FORT_HALL to "Fort Hall",
        Landmarks.FORT_BOISE to "Fort Boise",
        Landmarks.BLUE_MOUNTAINS to "Blue Mountains",
        Landmarks.FORT_WALLA_WALLA to "Fort Walla Walla",
        Landmarks.THE_DALLES to "The Dalles",
        Landmarks.KANSAS_RIVER to "Kansas River"
    )

    val assets = mapOf(
        Landmarks.INDEPENDENCE to resourcesVfs["landmarks/independence.jpg"],
        Landmarks.FORT_KEARNEY to resourcesVfs["landmarks/fort_kearney.png"],
        Landmarks.CHIMNEY_ROCK to resourcesVfs["landmarks/chimney_rock.png"],
        Landmarks.FORT_LARAMIE to resourcesVfs["landmarks/fort_laramie.png"],
        Landmarks.INDEPENDENCE_ROCK to resourcesVfs["landmarks/independence_rock.png"],
        Landmarks.SOUTH_PASS to resourcesVfs["landmarks/south_pass.png"],
        Landmarks.SODA_SPRINGS to resourcesVfs["landmarks/soda_springs.png"],
        Landmarks.FORT_BRIDGER to resourcesVfs["landmarks/fort_bridger.png"],
        Landmarks.FORT_HALL to resourcesVfs["landmarks/fort_hall.png"],
        Landmarks.FORT_BOISE to resourcesVfs["landmarks/fort_boise.png"],
        Landmarks.BLUE_MOUNTAINS to resourcesVfs["landmarks/blue_mountains.png"],
        Landmarks.FORT_WALLA_WALLA to resourcesVfs["landmarks/fort_walla_walla.png"],
        Landmarks.THE_DALLES to resourcesVfs["landmarks/the_dalles.png"],
        Landmarks.KANSAS_RIVER to resourcesVfs["landmarks/kansas_river.png"]
    )
}

data class Environment(val texture, val name, val length, val temp)

public class EnvironmentRegistry {
    val environments = listOf<Environment>()
    val landmarks = mapOf<Landmarks.Landmarks, Double>()
    val landmarkTypes = mapOf<>()
    val customTex = mapOf<>()

    fun add_environment(environment) {
        self.__environments.append(environment)
        return len(self.__environments) - 1
    }

    fun add_landmark(landmark, location, typeLandmark, customTexture = "") {
        self.__landmarks[landmark] = location
        self.__landmark_types[landmark] = typeLandmark
        if customTexture != "":
        self.__custom_tex[landmark] = resourcesVfs[customTexture)

        def get_environment (self, index):
        return self.__environments[index]
    }

    fun arriving(milesTraveled) {
        for landmark in self.__landmarks:
        if (self.__landmarks[landmark] * 100) -(milesTraveled * 4) < 100 and (self.__landmarks[landmark] * 100) - (milesTraveled * 4) > 0:
        return landmark
        return None
    }

    fun get_array() {
        return self.__environments
    }

    fun get_landmarks() {
        return self.__landmarks
    }

    fun get_type(landmark) {
        return self.__landmark_types[landmark]
    }

    fun get_texture(landmark) {
        return self.__custom_tex[landmark]
    }

    fun get_type_texture(self, type_landmark) {
        return Landmarks.textures[type_landmark]
    }

    fun get_length() {
        length = 0
        for env in self.__environments:
        length += env.length * 100
        return length
    }
}

class Ground {
    fun render(environments, registry) {
        landmarks = registry.get_landmarks()
        length = 0
        for env in environments:
        length += env.length * 100
        length = length * 5
        screen = pygame.Surface((length, 80), pygame.SRCALPHA, 32)
        cursor = 0
        for environment in environments:
        for i in range(environment.length * 100):
        screen.blit(environment.texture, (cursor, 50))
        cursor += 5
        for landmark in landmarks:
        if registry.get_type(landmark) == Landmarks.Types.RIVER:
        screen.blit(registry.get_type_texture(registry.get_type(landmark)), (landmarks[landmark] * 100, 50))
        elif registry . get_type (landmark) == Landmarks.Types.TEXTURED:
        screen.blit(util.imgscale(registry.get_texture(landmark), 0.75), (landmarks[landmark] * 100, 0))
        else:
        screen.blit(registry.get_type_texture(registry.get_type(landmark)), (landmarks[landmark] * 100, 0))
        return pygame.transform.flip(screen, True, False)
    }
}