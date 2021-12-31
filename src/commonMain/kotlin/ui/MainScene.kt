package ui

import com.soywiz.klock.timesPerSecond
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.ui.uiButton
import com.soywiz.korge.view.*

class MainScene : Scene() {
    private lateinit var shipContainer: Container
    private lateinit var controls: Container
    private val shipViewSize = 500

    override suspend fun Container.sceneInit() {
        fixedSizeContainer(VIRTUAL_SIZE, VIRTUAL_SIZE, clip = false) {
            controls = fixedSizeContainer(300, VIRTUAL_SIZE - 40, clip = true) {
            }
            shipContainer = fixedSizeContainer(shipViewSize, shipViewSize, clip = true) {
                alignLeftToRightOf(controls)
            }
            uiButton(text = "Planet") {
                alignTopToBottomOf(controls)
                onPress {
                }
            }
        }

        addFixedUpdater(10.timesPerSecond) {
            tick()
        }
    }

    private fun tick() {
    }


}