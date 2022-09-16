package me.jackrichard.oregontrail.data

import com.soywiz.korio.file.VfsFile

data class Environment(val texture: VfsFile, val name: String, val length: Int, val temp: Double)