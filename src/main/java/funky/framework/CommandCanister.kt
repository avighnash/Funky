package funky.framework

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent

data class CommandCanister(val event: MessageReceivedEvent, val args: List<String>)