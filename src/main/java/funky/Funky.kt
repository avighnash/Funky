package funky

import sx.blah.discord.api.ClientBuilder
import sx.blah.discord.api.IDiscordClient
import sx.blah.discord.api.events.EventSubscriber
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent
import sx.blah.discord.util.DiscordException

class Funky {

    companion object {

        fun create(token: String, login: Boolean): IDiscordClient? {
            val builder = ClientBuilder()
            builder.withToken(token)

            try {
                return if (login) {
                    builder.login()
                }
                else {
                    builder.build()
                }
            }
            catch (e: DiscordException) {
                e.printStackTrace()
                return null
            }
        }
    }
}