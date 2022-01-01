import com.soywiz.korau.sound.Sound
import com.soywiz.korau.sound.readSound
import com.soywiz.korio.file.std.resourcesVfs

enum class Instrument(private val file: String){
    PIANO("music/piano.mp3");

    suspend fun loadSound(): Sound {
        return resourcesVfs[file].readSound()
    }
}

enum class Pitch(val pitch: Double){
    LOWEST(.5),
    LOWER(.7),
    NORMAL(1.0),
    HIGHER(1.5),
    HIGHEST(2.0)
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