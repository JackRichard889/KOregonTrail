package me.jackrichard.oregontrail.data

import com.soywiz.korio.file.VfsFile
import com.soywiz.korio.file.std.resourcesVfs

class EnvironmentRegistry {
    val environments = mutableListOf<Environment>()
    val landmarks = mutableMapOf<Landmarks.Landmarks, Int>()
    val landmarkTypes = mutableMapOf<Landmarks.Landmarks, Landmarks.Types>()
    val customTex = mutableMapOf<Landmarks.Landmarks, VfsFile>()

    fun addEnvironment(environment: Environment) : Int {
        environments.add(environment)
        return environments.size - 1
    }

    fun addLandmark(landmark: Landmarks.Landmarks, location: Int, typeLandmark: Landmarks.Types, customTexture: String = "") {
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

    fun get_type(landmark: Landmarks.Landmarks) = landmarkTypes[landmark]

    fun get_texture(landmark: Landmarks.Landmarks) = customTex[landmark]

    fun get_type_texture(type_landmark: Landmarks.Types): VfsFile {
        return Landmarks.textures[type_landmark]!!
    }

    fun length() = environments.sumOf { it.length * 100 }
}