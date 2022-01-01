package ui

import BeatPlayer
import Board
import MusicBuilder
import com.soywiz.klock.timesPerSecond
import com.soywiz.korau.sound.*
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.ui.uiButton
import com.soywiz.korge.view.*
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korio.file.std.resourcesVfs

class MainScene : Scene() {
    private lateinit var shipContainer: Container
    private lateinit var controls: Container
    private val shipViewSize = 500
    private lateinit var music: Sound
    private val builder = MusicBuilder()
    private val player = BeatPlayer()

    override suspend fun Container.sceneInit() {
        builder.boards[0] = Board()

        fixedSizeContainer(VIRTUAL_SIZE, VIRTUAL_SIZE, clip = false) {
            controls = fixedSizeContainer(300, VIRTUAL_SIZE - 40, clip = true) {
            }
            shipContainer = fixedSizeContainer(shipViewSize, shipViewSize, clip = true) {
                alignLeftToRightOf(controls)
            }
            uiButton(text = "C") {
                alignTopToBottomOf(controls)
                onPress {
                    builder.boards[0]?.toggleNote(1, 1.0)
                }
            }
        }

        addFixedUpdater(2.timesPerSecond) {
            launchImmediately {
                player.play(builder.buildMusic())
            }
        }
    }



}