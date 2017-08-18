package funky.util

import ca.szc.configparser.Ini
import java.io.File
import java.nio.file.Paths

object Config {

    fun getConfig(): Ini {
        val loader = javaClass.classLoader
        val input = Paths.get(loader.getResource("bot.cfg").path)

        return Ini().read(input)
    }
}