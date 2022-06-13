import com.soywiz.korev.Key
import com.soywiz.korge.Korge
import com.soywiz.korge.scene.Module
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.addUpdater
import com.soywiz.korge.view.text
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.font.TtfFont
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.ISizeInt
import screens.LearnScene

suspend fun main() = Korge(Korge.Config(windowSize = ISizeInt.invoke(500, 500), virtualSize = ISizeInt.invoke(500, 500), bgcolor = Colors["#000000"], title = "Oregon Trail 2020 Remastered Ultimate Edition", module = GameModule))

class MainScene : Scene() {
	override suspend fun Container.sceneInit() {
		val font = TtfFont(resourcesVfs["font.ttf"].readAll())

		text("The Oregon Trail", textSize = 29.0, font = font, color = RGBA.float(255.0 / 255.0, 255.0 / 255.0, 150.0 / 255.0)) {
			x = 24.0
			y = 40.0
		}

		text("You may:\n\n\n  1. Travel the trail\n  2. Learn about the trail\n  3. End\n\n\nWhat is your choice? _", textSize = 18.0, color = RGBA.float(1.0, 1.0, 1.0), font = font) {
			x = 35.0
			y = 120.0
		}

		addUpdater {
			when {
				//views.input.keys[Key.NUMPAD1] -> sceneContainer.changeTo()
				views.input.keys[Key.NUMPAD2] -> launchImmediately { sceneContainer.pushTo<LearnScene>() }
				views.input.keys[Key.NUMPAD3] -> views.gameWindow.close(0)
			}

			return@addUpdater
		}
	}
}

object GameModule : Module() {
	override val mainScene = MainScene::class

	override suspend fun AsyncInjector.configure() {
		mapPrototype { MainScene() }
		mapPrototype { LearnScene() }
	}
}