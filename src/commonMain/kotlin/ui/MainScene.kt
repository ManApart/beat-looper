package ui

import BeatPlayer
import Board
import MusicBuilder
import Pitch
import com.soywiz.klock.timesPerSecond
import com.soywiz.korau.sound.*
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.ui.uiButton
import com.soywiz.korge.view.*
import com.soywiz.korio.async.launchImmediately

class MainScene : Scene() {
    private val builder = MusicBuilder()
    private val player = BeatPlayer()

    override suspend fun Container.sceneInit() {
        builder.boards[0] = Board()

        fixedSizeContainer(VIRTUAL_SIZE, VIRTUAL_SIZE, clip = false) {
            builder.boards.values.forEach { board ->
                fixedSizeContainer(300, VIRTUAL_SIZE - 40, clip = false) {
                    (0 until board.stepCount()).forEach { x ->
                        (0 until Pitch.values().size).forEach { y ->
                            uiButton(text = "$x,$y") {
                                xy(x * 100, y * 50)
                                onPress {
                                    builder.boards[0]?.toggleNote(x, Pitch.values()[y].pitch)
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