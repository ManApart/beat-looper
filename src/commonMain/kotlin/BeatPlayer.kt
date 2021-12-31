data class Note(val instrument: Int, val pitch: Int)

class Step(val notes: List<Note> = listOf())

class Music(val steps: List<Step>)

class BeatPlayer(val music: Music) {
    var step = 0

    fun play() {
        val notes = music.steps[step].notes
        step += 1
        if (step >= music.steps.size) step = 0

        //Play music
        println(notes.joinToString())
    }
}