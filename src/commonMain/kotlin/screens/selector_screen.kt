package screens

import GameState
import com.soywiz.korev.Key
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.addUpdater
import com.soywiz.korge.view.text
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.font.TtfFont
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korio.file.std.resourcesVfs

class SelectorScene(val gameState: GameState) : Scene() {
    override suspend fun Container.sceneInit() {
        val font = TtfFont(resourcesVfs["font.ttf"].readAll())

        text("The Oregon Trail", font = font, textSize = 29.0, color = RGBA.float(255.0 / 255.0, 255.0 / 255.0, 150.0 / 255.0)) {
            x = 24.0
            y = 40.0
        }

        text("Many types of people made\nthe trip to Oregon.\n\n\nYou may:\n\n\n  1. Be a banker from\nBoston\n  2. Be a carpenter from\nOhio\n  3. Be a farmer from\nIllinois\n\n\nWhat is your choice? _", font = font, textSize = 18.0, color = Colors.WHITE) {
            x = 35.0
            y = 120.0
        }

        addUpdater {
            if (views.input.keys.justPressed(Key.NUMPAD1)) {
                gameState.extras["occupation"] = 1
                launchImmediately { sceneContainer.pushTo<NameSelectorScene>() }
            } else if (views.input.keys.justPressed(Key.NUMPAD2)) {
                gameState.extras["occupation"] = 2
                launchImmediately { sceneContainer.pushTo<NameSelectorScene>() }
            } else if (views.input.keys.justPressed(Key.NUMPAD3)) {
                gameState.extras["occupation"] = 3
                launchImmediately { sceneContainer.pushTo<NameSelectorScene>() }
            }
        }
    }
}