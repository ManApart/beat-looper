import com.soywiz.korau.sound.Sound

class Board(private val instrument: Instrument, stepCount: Int = 8) {
    private val steps = mutableMapOf<Int, MutableList<Key>>()

    init {
        (0..stepCount).forEach {
            steps[it] = mutableListOf()
        }
    }

    fun toggleNote(step: Int, key: Key) {
        if (steps[step]?.contains(key) == true) {
            removeNote(step, key)
        } else {
            addNote(step, key)
        }

    }

    fun addNote(step: Int, key: Key) {
        steps.getOrPut(step) { mutableListOf() }
        steps[step]?.add(key)
    }

    fun removeNote(step: Int, key: Key) {
        steps[step]?.remove(key)
    }

    fun getNotes(step: Int): List<Sound> {
        return steps[step]?.map { key -> instrument.getSample(key) } ?: listOf()
    }

    fun stepCount(): Int {
        return steps.keys.size
    }

}

class MusicBuilder {
    val boards = mutableMapOf<Int, Board>()

    suspend fun buildMusic(): Music {
        val maxSteps = boards.values.maxOf { it.stepCount() }
        val steps = (0 until maxSteps).map { i ->
            Step(boards.values.flatMap { it.getNotes(i) })
        }
        return Music(steps)
    }
}

class BeatPlayer {
    var step = 0

    suspend fun play(music: Music) {
        if (step >= music.steps.size) step = 0

        val notes = music.steps[step].notes
        notes.forEach { it.play() }

        step += 1
    }
}