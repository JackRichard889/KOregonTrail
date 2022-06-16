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

class LearnScene(gameState: GameState) : Scene() {
    var page: Int = 0

    override suspend fun Container.sceneInit() {
        val font = TtfFont(resourcesVfs["font.ttf"].readAll())

        text("The Oregon Trail", textSize = 29.0, font = font, color = RGBA.float(255.0 / 255.0, 255.0 / 255.0, 150.0 / 255.0)) {
            x = 24.0
            y = 40.0
        }

        val learnText = text("Try taking a journey by\ncovered wagon across 2000\nmiles of plains, rivers,\nand mountains. Try! On\nthe plains, will you sloth\nyour oxen through mud and\nwater-filled ruts or will\nyou plod through dust six\ninches deep?", textSize = 18.0, font = font, color = RGBA.float(1.0, 1.0, 1.0)) {
            x = 35.0
            y = 120.0
        }

        text("Press SPACE to continue.", textSize = 15.0, color = Colors.WHITE, font = font) {
            x = 75.0
            y = 475.0
        }

        addUpdater {
            if (views.input.keys.justPressed(Key.SPACE)) { page++ }
            learnText.text = when (page) {
                0 -> "Try taking a journey by\ncovered wagon across 2000\nmiles of plains, rivers,\nand mountains. Try! On\nthe plains, will you sloth\nyour oxen through mud and\nwater-filled ruts or will\nyou plod through dust six\ninches deep?"
                1 -> "How will you cross the\nrivers? If you have money,\nyou might take a ferry (if\nthere is a ferry). Or,\nyou can ford the river\nand hope you and your\nwagon aren't swallowed\nalive!\n\nWhat about supplies? Well,\nif you're low on food you\ncan hunt. You might get a\nbuffalo, you might. And\nthere are bears in\nthe mountains."
                2 -> "At the Dalles, you can try\nnavigating the Columbia\nRiver, but if running the\nrapids with a makeshift\nraft makes you queasy,\nbetter take the Barlow\nRoad.\n\nIf for some reason you\ndon't survive -- your\nwagon burns, or thieves\nsteal your oxen, or you\nrun out of provisions, or\nyou die of cholera --\ndon't give up! Try again\n...and again...and see how\nhigh your score can get."
                3 -> "Developed by:\n\nJack Richard\nSeamus Smith\nBenedict Antwi\nDamian Jadczak\nGabriel Palomeque Jara\nPrince Nanakobi Jr\n\nRewritten in Kotlin\nby Jack Richard\n\nPowered by KorGE"
                else -> { launchImmediately { sceneContainer.back() }; "" }
            }
        }
    }
}