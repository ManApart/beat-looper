class Board {
    var instrument = 0

    private val steps = mutableMapOf<Int, MutableList<Int>>()

    fun addNote(step: Int, pitch: Int){
        steps.getOrPut(step) { mutableListOf() }
        steps[step]?.add(pitch)
    }

    fun removeNote(step: Int, pitch: Int){
        steps[step]?.remove(pitch)
    }

    fun getNotes(step: Int): List<Note>{
        return steps[step]?.map { pitch -> Note(instrument, pitch) } ?: listOf()
    }

    fun stepCount(): Int {
        return steps.keys.size
    }

}

class MusicBuilder {
    val boards = mutableMapOf<Int, Board>()

    fun buildMusic(): Music{
        val maxSteps = boards.values.maxOf { it.stepCount() }
        val steps = (0 until maxSteps).map { i ->
            Step(boards.values.flatMap { it.getNotes(i) })
        }
        return Music(steps)
    }
}