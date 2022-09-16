package me.jackrichard.oregontrail

import me.jackrichard.oregontrail.data.GameState
import com.soywiz.korge.Korge
import com.soywiz.korge.scene.Module
import com.soywiz.korim.color.Colors
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korma.geom.ISizeInt
import me.jackrichard.oregontrail.scenes.*

suspend fun main() = Korge(Korge.Config(windowSize = ISizeInt.invoke(500, 500), virtualSize = ISizeInt.invoke(500, 500), bgcolor = Colors["#000000"], title = "Oregon Trail 2020 Remastered Ultimate Edition", module = GameModule))

object GameModule : Module() {
	override val mainScene = MainScene::class

	override suspend fun AsyncInjector.configure() {
		mapInstance(GameState)
		mapPrototype { MainScene(GameState) }
		mapPrototype { LearnScene(GameState) }
		mapPrototype { SelectorScene(GameState) }
		mapPrototype { NameSelectorScene(GameState) }
		mapPrototype { ShopScene(GameState) }
	}
}