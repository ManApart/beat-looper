import com.soywiz.korau.sound.Sound

data class Note(val instrument: Sound, val pitch: Double) {
    suspend fun play(){
        instrument.pitch = pitch
        instrument.play()
    }
}

class Step(val notes: List<Note> = listOf())

class Music(val steps: List<Step>)

class BeatPlayer(val music: Music) {
    var step = 0

    suspend fun play() {
        val notes = music.steps[step].notes
        step += 1
        if (step >= music.steps.size) step = 0

        notes.forEach { it.play() }
    }
}