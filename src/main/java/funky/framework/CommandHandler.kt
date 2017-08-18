package funky.framework

import sx.blah.discord.api.events.EventSubscriber
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent

class CommandHandler {

    @EventSubscriber
    fun commandHandler(e: MessageReceivedEvent) {

        if (e.message.toString().startsWith("/")) {

            CommandRegister.classes.forEach { c ->

                c.java.methods.filter {
                    it.isAnnotationPresent(Command::class.java)
                            && it.parameters[0].type == CommandCanister::class.java
                }.forEach { method ->

                    val splitMessage = e.message.toString().split(' ').toMutableList()

                    if (method.getAnnotation(Command::class.java).cmd.toLowerCase() == splitMessage[0]) {

                        splitMessage.removeAt(0)

                        val args: List<String> = splitMessage

                        val canister = CommandCanister(e, args)
                        try {
                            method.invoke(c.java.newInstance(), canister)
                        } catch (err: NullPointerException) {
                            println(err)
                        }
                    }
                }
            }
        }
    }
}