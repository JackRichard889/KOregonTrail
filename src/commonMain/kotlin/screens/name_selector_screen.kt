package screens

import GameState
import Person
import com.soywiz.klock.milliseconds
import com.soywiz.korev.Key
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korge.view.tween.hide
import com.soywiz.korge.view.tween.show
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.font.TtfFont
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korio.file.std.resourcesVfs
import getPressedKeys

class NameSelectorScene(val gameState: GameState) : Scene() {
    var currentInx = 0
    var names = mutableListOf("", "", "", "", "")

    override suspend fun Container.sceneInit() {
        val font = TtfFont(resourcesVfs["font.ttf"].readAll())
        val allNames = resourcesVfs["names.txt"].readLines().toList()

        text("The Oregon Trail", font = font, textSize = 29.0, color = RGBA.float(255.0 / 255.0, 255.0 / 255.0, 150.0 / 255.0)) {
            x = 24.0
            y = 40.0
        }

        image(resourcesVfs["mama.png"].readBitmap()) {
            x = 50.0
            y = 80.0
        }

        image(resourcesVfs["gunman.png"].readBitmap()) {
            x = 350.0
            y = 90.0
            scale = 1.5
        }

        image(resourcesVfs["wagon.png"].readBitmap()) {
            x = 200.0
            y = 80.0
            scale = 3.0
        }

        image(resourcesVfs["oxen.png"].readBitmap()) {
            x = 170.0
            y = 110.0
            scale = 1.5
        }

        val elements = (0..4).map {
            text(if (it == 0) "What is the first name of\nthe wagon leader? " + names[it].ifEmpty { "_" } else "#${it + 1}: " + names[it].ifEmpty { "_" },
                font = font,
                textSize = 18.0,
                color = Colors.WHITE) {
                x = 25.0
                y = if (it == 0) 240.0 else 300.0 + (it * 30)
            }
        }.also { it.forEachIndexed { index, text -> if (index != 0) { text.hide(time = 0.milliseconds) } } }

        val nameChecker = text("Are these names correct? _", font = font, textSize = 18.0, color = Colors.WHITE) {
            x = 25.0
            y = 475.0
        }.also { it.hide(time = 0.milliseconds) }

        addUpdater {
            if (views.input.keys.justPressed(Key.ENTER) || views.input.keys.justPressed(Key.RETURN)) {
                if (currentInx <= 5) {
                    if (names[currentInx].isBlank()) {
                        names[currentInx] = allNames.random().lowercase()
                            .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
                    }

                    elements.forEachIndexed { index, text ->
                        text.text = if (index == 0) "What is the first name of\nthe wagon leader? " + names[index].ifEmpty { "_" } else "#${index + 1}: " + names[index].ifEmpty { "_" }
                    }

                    elements.take(++currentInx + 1).forEach { launchImmediately { it.show(time = 0.milliseconds) } }
                }
            }

            if (currentInx != 5) {
                names[currentInx] = names[currentInx] + getPressedKeys(views.input.keys)
                if (views.input.keys.justPressed(Key.BACKSPACE) && names[currentInx].isNotEmpty()) { names[currentInx] = names[currentInx].dropLast(1) }
                elements.forEachIndexed { index, text ->
                    text.text = if (index == 0) "What is the first name of\nthe wagon leader? " + names[index].ifEmpty { "_" } else "#${index + 1}: " + names[index].ifEmpty { "_" }
                }
            } else {
                launchImmediately { nameChecker.show(time = 0.milliseconds) }
                if (views.input.keys.justPressed(Key.N)) {
                    names = mutableListOf("", "", "", "", "")
                    currentInx = 0
                    elements.forEachIndexed { index, text -> if (index != 0) { launchImmediately { text.hide(time = 0.milliseconds) } } }
                    launchImmediately { nameChecker.hide(time = 0.milliseconds) }
                } else if (views.input.keys.justPressed(Key.Y)) {
                    gameState.wagon.party = List(5) { Person(names[it], isMainPerson = it == 0, occupation = if (it == 0) GameState.extras["occupation"] as Int else 0) }
                    launchImmediately {
                        sceneContainer.pushTo<ShopScene>()
                    }
                }
            }

        }
    }
}