class Board(private val instrument: Instrument = Instrument.PIANO, stepCount: Int = 8) {
    private val steps = mutableMapOf<Int, MutableList<Double>>()

    init {
        (0..stepCount).forEach {
            steps[it] = mutableListOf()
        }
    }

    fun toggleNote(step: Int, pitch: Double) {
        if (steps[step]?.contains(pitch) == true) {
            removeNote(step, pitch)
        } else {
            addNote(step, pitch)
        }

    }

    fun addNote(step: Int, pitch: Double) {
        steps.getOrPut(step) { mutableListOf() }
        steps[step]?.add(pitch)
    }

    fun removeNote(step: Int, pitch: Double) {
        steps[step]?.remove(pitch)
    }

    suspend fun getNotes(step: Int): List<Note> {
        return steps[step]?.map { pitch -> Note(instrument.loadSound(), pitch) } ?: listOf()
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