package me.jackrichard.oregontrail.scenes

import me.jackrichard.oregontrail.data.GameState
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korim.font.TtfFont
import com.soywiz.korio.file.std.resourcesVfs

class ShopScene(gameState: GameState) : Scene() {
    override suspend fun Container.sceneInit() {
        val font = TtfFont(resourcesVfs["font.ttf"].readAll())


    }
}