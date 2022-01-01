package ui

import com.soywiz.klock.timesPerSecond
import com.soywiz.korau.sound.*
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.ui.uiButton
import com.soywiz.korge.view.*
import com.soywiz.korio.file.std.resourcesVfs

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

        val music = resourcesVfs["music/test.mp3"].readSound()

        music.pitch = 2.1
        music.playForever()
//        val audioOutput: PlatformAudioOutput = nativeSoundProvider.createAudioStream(freq = 44100)
//        audioOutput.start()
//        var i = 0
//        while (i < 1000) {
//            i++
//            // Then you have to add samples. This function suspends, and resumes when it needs more data so it can play the data continuously.
//            // Here you can do raw manipulation, DSP, manual effects and other stuff.
//            audioOutput.pitch
//            audioOutput.add(AudioSamples(1, 100, (0..100).)
//        }
//
//        audioOutput.stop()
    }

    private fun tick() {
    }


}