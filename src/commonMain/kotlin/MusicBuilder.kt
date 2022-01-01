import com.soywiz.korau.sound.readSound
import com.soywiz.korio.file.std.resourcesVfs

class Board {
    var instrumentName = "music/test.mp3"

    private val steps = mutableMapOf<Int, MutableList<Double>>()

    fun addNote(step: Int, pitch: Double){
        steps.getOrPut(step) { mutableListOf() }
        steps[step]?.add(pitch)
    }

    fun removeNote(step: Int, pitch: Double){
        steps[step]?.remove(pitch)
    }

    suspend fun getNotes(step: Int): List<Note>{
        return steps[step]?.map { pitch -> Note(resourcesVfs["music/test.mp3"].readSound(), pitch) } ?: listOf()
    }

    fun stepCount(): Int {
        return steps.keys.size
    }

}

class MusicBuilder {
    val boards = mutableMapOf<Int, Board>()

    suspend fun buildMusic(): Music{
        val maxSteps = boards.values.maxOf { it.stepCount() }
        val steps = (0 until maxSteps).map { i ->
            Step(boards.values.flatMap { it.getNotes(i) })
        }
        return Music(steps)
    }
}