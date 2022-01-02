import com.soywiz.korau.sound.Sound
import com.soywiz.korau.sound.readSound
import com.soywiz.korio.file.std.resourcesVfs

enum class Key {
    A, B, C, D, E, F, G
}

class Instrument(private val folderName: String) {
    private var samples = mapOf<Key, Sound>()

    suspend fun loadSamples() {
        samples = Key.values().associateWith { key -> resourcesVfs["music/$folderName/${key.name.lowercase()}.mp3"].readSound() }
    }

    fun getSample(key: Key): Sound {
        return samples[key]!!
    }

}

class Step(val notes: List<Sound> = listOf())

class Music(val steps: List<Step>)