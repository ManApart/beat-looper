package ui

import com.soywiz.korge.scene.Module
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korma.geom.*

const val WINDOW_WIDTH = 1000
const val WINDOW_HEIGHT = 800
const val VIRTUAL_SIZE = 640

object MainModule : Module() {
    override val mainScene = MainScene::class
    override val title: String = "Beat Looper"
    override val size: SizeInt = SizeInt(Size(WINDOW_WIDTH, WINDOW_HEIGHT))
    override val windowSize = size
    override val icon: String = "images/icon.png"
    override val scaleMode: ScaleMode = ScaleMode.NO_SCALE
    override val clipBorders: Boolean = false
    override val scaleAnchor: Anchor = Anchor.TOP_LEFT

    override suspend fun AsyncInjector.configure() {
        mapPrototype { MainScene() }
    }
}