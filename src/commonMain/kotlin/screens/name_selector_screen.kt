package screens

import com.soywiz.korev.Key
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.font.TtfFont
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import getPressedKeys

class NameSelectorScene : Scene() {
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

        var elements = (0..if (currentInx != 0) 4 else 0).map {
            text(if (it == 0) "What is the first name of\nthe wagon leader? " + names[it].ifEmpty { "_" } else "#${it + 1}: " + names[it].ifEmpty { "_" },
                font = font,
                textSize = 18.0,
                color = Colors.WHITE) {
                x = 25.0
                y = if (currentInx == 0) 240.0 else 300.0 + (it * 30)
            }
        }

        addUpdater {
            if (views.input.keys.justPressed(Key.ENTER) || views.input.keys.justPressed(Key.RETURN)) {
                if (currentInx < 5) {
                    if (names[currentInx].isBlank()) {
                        names[currentInx] = allNames.random().uppercase()
                    }
                }

                if (++currentInx == 1) {
                    elements = elements + (1..4).map {
                        text("#${it + 1}: " + names[it].ifEmpty { "_" },
                            font = font,
                            textSize = 18.0,
                            color = Colors.WHITE) {
                            x = 25.0
                            y = if (currentInx == 0) 240.0 else 300.0 + (it * 30)
                        }
                    }
                }
            }

            if (currentInx != 5) {
                names[currentInx] = names[currentInx] + getPressedKeys(views.input.keys)
                if (views.input.keys.justPressed(Key.BACKSPACE) && names[currentInx].isNotEmpty()) { names[currentInx] = names[currentInx].dropLast(1) }
            } else {
                text("Are these names correct? _", font = font, textSize = 18.0, color = Colors.WHITE) {
                    x = 25.0
                    y = 475.0
                }
            }

            elements.forEachIndexed { index, text ->
                text.text = if (index == 0) "What is the first name of\nthe wagon leader? " + names[index].ifEmpty { "_" } else "#${index + 1}: " + names[index].ifEmpty { "_" }
            }
        }
    }

    /*def process_input(self, key):
    if key == pygame.K_RETURN:
    if self.selected < 4:
    if self.names[self.selected] == " " or self.names[self.selected] == "":
    self.names[self.selected] = util.random_name()
    self.selected += 1
    else:
    if self.names[self.selected] == " " or self.names[self.selected] == "":
    self.names[self.selected] = util.random_name()
    self.selected = 5
    elif key == pygame.K_BACKSPACE:
    if len(self.names[self.selected]) > 0:
    self.names[self.selected] = self.names[self.selected][:-1]
    elif len(self.names[self.selected]) < 12 and util.is_letter(key):
    charKey = str(chr(key))
    mods = pygame.key.get_mods()
    if pygame.key.get_pressed()[pygame.K_LSHIFT] or pygame.key.get_pressed()[pygame.K_RSHIFT] or mods & pygame.KMOD_CAPS:
    charKey = charKey.upper()
    if not self.selected == 5:
    self.names[self.selected] += charKey
    else:
    if charKey == "y":
    self.names.pop()
    people = util.array_to_person(self.names)
    people[0].occupation = self.data ["occupation"]
    del self.data ["occupation"]
    self.data ["wagon"] = Wagon(people, people[0])
    self.next = ShopScreen(self.data )
    self.idle = True
    elif charKey == "n":
    self.names = ["", "", "", "", "", ""]
    self.selected = 0*/
}