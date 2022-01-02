package ui

import BeatPlayer
import Board
import Instrument
import MusicBuilder
import com.soywiz.klock.timesPerSecond
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.ui.uiButton
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.addFixedUpdater
import com.soywiz.korge.view.fixedSizeContainer
import com.soywiz.korge.view.xy
import com.soywiz.korio.async.launchImmediately
import kotlin.collections.set

class MainScene : Scene() {
    private val builder = MusicBuilder()
    private val player = BeatPlayer()
    private val boardHeight = 400

    override suspend fun Container.sceneInit() {
        val piano = Instrument("piano").also { it.loadSamples() }
        val drum = Instrument("drum").also { it.loadSamples() }

        builder.boards[0] = Board(piano)
        builder.boards[1] = Board(drum)

        fixedSizeContainer(VIRTUAL_SIZE, VIRTUAL_SIZE, clip = false) {
            builder.boards.values.forEachIndexed { i, board ->
                fixedSizeContainer(300, boardHeight, clip = false) {
                    xy(0, i * boardHeight)
                    (0 until board.stepCount()).forEach { x ->
                        (0 until Key.values().size).forEach { y ->
                            var pressed = false
                            uiButton(text = "") {
                                xy(x * 100, y * 50)
                                onPress {
                                    board.toggleNote(x, Key.values()[Key.values().size-1 - y])
                                    pressed = !pressed
                                    text = if (pressed) "00000" else ""
                                }
                            }
                        }
                    }
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