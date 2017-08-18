package funky

import funky.framework.Command
import funky.framework.CommandCanister
import funky.framework.CommandHandler
import funky.framework.CommandRegister
import funky.util.Config
import org.apache.commons.lang3.StringUtils
import sx.blah.discord.util.EmbedBuilder
import funky.util.GithubUtil
import java.awt.Color

class Main {

    @Command("/math") fun math(canister: CommandCanister) {
        val args = canister.args

        if (StringUtils.isNumeric(args[0]) && args[1].toCharArray()[0] is Char && StringUtils.isNumeric(args[2])) {
            val one = args[0].toDouble()
            val two = args[2].toDouble()

            val res: Double
            when (args[1].toCharArray()[0]) {

                '+' -> res = one + two
                '-' -> res = one - two
                '*' -> res = one * two
                '/' -> res = one * two

                else -> res = 0.0
            }

            canister.event.channel.sendMessage(res.toString())
        }
    }

    @Command("/github") fun github(canister: CommandCanister) {
        val args = canister.args

        val github = GithubUtil.searchForRepo(args[0])
        val results =  github.subList(1, 6)

        var str: String = ""

        results.forEach {
            str = str + it.get("html_url") + "\n"
        }

        val builder = EmbedBuilder()
        builder.withColor(Color.BLUE)
        builder.appendField("Github Repo Search Results", str, true)

        canister.event.channel.sendMessage(builder.build())
    }
}


fun main(args: Array<String>) {

    val cfg = Config.getConfig()
    val sections: Map<String, Map<String, String>> = cfg.sections

    val token = sections.get("Bot")!!.get("token").toString()

    println(token)

    val client = Funky.create(token, true)
    client!!.dispatcher.registerListener(CommandHandler())

    GithubUtil.searchForRepo("serenity")

    CommandRegister.register(Main::class)
}
