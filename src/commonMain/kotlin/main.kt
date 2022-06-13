import com.soywiz.korge.Korge
import com.soywiz.korge.view.text
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.font.TtfFont
import com.soywiz.korio.file.std.resourcesVfs

suspend fun main() = Korge(virtualWidth = 500, virtualHeight = 500, width = 500, height = 500, bgcolor = Colors["#000000"], title = "Oregon Trail 2020 Remastered Ultimate Edition") {
	val font = TtfFont(resourcesVfs["font.ttf"].readAll())

	text("The Oregon Trail", textSize = 29.0, font = font, color = RGBA.float(255.0 / 255.0, 255.0 / 255.0, 150.0 / 255.0)) {
		x = 24.0
		y = 40.0
	}

	text("You may:\n\n\n  1. Travel the trail\n  2. Learn about the trail\n  3. End\n\n\nWhat is your choice? _", textSize = 18.0, color = RGBA.float(1.0, 1.0, 1.0), font = font) {
		x = 35.0
		y = 120.0
	}
}