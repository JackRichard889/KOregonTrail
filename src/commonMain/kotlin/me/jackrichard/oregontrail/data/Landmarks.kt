package me.jackrichard.oregontrail.data

import com.soywiz.korio.file.std.resourcesVfs

class Landmarks {
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