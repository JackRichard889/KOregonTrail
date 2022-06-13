import com.soywiz.korio.file.VfsFile
import com.soywiz.korio.file.std.resourcesVfs

public class Landmarks {
    enum class Types {
        RIVER,
        FORT,
        TEXTURED
    }

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

    companion object {
        val textures = mapOf(
            Types.RIVER to resourcesVfs["location/river.png"],
            Types.FORT to resourcesVfs["location/fort.png"]
        )
    }
}

data class Environment(val texture: VfsFile, val name: String, val length: Int, val temp: Double)

public class EnvironmentRegistry {
    val environments = mutableListOf<Environment>()
    val landmarks = mutableMapOf<Landmarks.Landmarks, Int>()
    val landmarkTypes = mutableMapOf<Landmarks.Landmarks, Landmarks.Types>()
    val customTex = mutableMapOf<Landmarks.Landmarks, VfsFile>()

    fun add_environment(environment: Environment) : Int {
        environments.add(environment)
        return environments.size - 1
    }

    fun add_landmark(landmark: Landmarks.Landmarks, location: Int, typeLandmark: Landmarks.Types, customTexture: String = "") {
        landmarks[landmark] = location
        landmarkTypes[landmark] = typeLandmark
        if (customTexture != "") {
            customTex[landmark] = resourcesVfs[customTexture]
        }
    }

    fun get_environment(index: Int) = environments[index]

    fun arriving(milesTraveled: Int) : Landmarks.Landmarks? {
        landmarks.keys.forEach {
            if ((landmarks[it]?.times(100))!! - (milesTraveled * 4) in 1..99) {
                return@arriving it
            }
        }
        return null
    }

    fun get_array() = environments

    fun get_landmarks() = landmarks

    fun get_type(landmark: Landmarks.Landmarks) = landmarkTypes[landmark]

    fun get_texture(landmark: Landmarks.Landmarks) = customTex[landmark]

    fun get_type_texture(type_landmark: Landmarks.Types): VfsFile {
        return Landmarks.textures[type_landmark]!!
    }

    fun get_length() = environments.sumOf { it.length * 100 }
}

class Ground {
    /*fun render(environments, registry) {
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
    }*/
}