import com.soywiz.korau.sound.Sound
import com.soywiz.korau.sound.readSound
import com.soywiz.korio.file.std.resourcesVfs

enum class Instrument(private val file: String){
    PIANO("music/piano.mp3");

    suspend fun loadSound(): Sound {
        return resourcesVfs[file].readSound()
    }
}


data class Note(val instrument: Sound, val pitch: Double) {
    suspend fun play(){
        instrument.pitch = pitch
        instrument.play()
    }
}

class Step(val notes: List<Note> = listOf())

class Music(val steps: List<Step>)

class BeatPlayer {
    var step = 0

    suspend fun play(music: Music) {
        if (step >= music.steps.size) step = 0

        val notes = music.steps[step].notes
        notes.forEach { it.play() }

        step += 1
    }
}