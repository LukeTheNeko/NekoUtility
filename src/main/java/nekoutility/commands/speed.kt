package nekoutility.commands

import nekoutility.Main.Companion.send
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class speed : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player) {
            val player = sender
            if (args.size == 2 && player.hasPermission("nekoutility.speed")) {
                val mode = args[0]
                val level = args[1].toIntOrNull()

                if (mode.equals("walk", ignoreCase = true) || mode.equals("fly", ignoreCase = true)) {
                    if (level != null && level in 0..10) {
                        val speed = when (mode.toLowerCase()) {
                            "walk" -> level / 10.0f
                            "fly" -> level / 20.0f
                            else -> 0.0f
                        }

                        player.run {
                            if (mode.equals("walk", ignoreCase = true)) {
                                walkSpeed = speed
                                send(player, "walk-speed-set", level.toString())
                            } else {
                                flySpeed = speed
                                send(player, "fly-speed-set", level.toString())
                            }
                        }
                        return true
                    } else {
                        send(player, "invalid-speed-level")
                    }
                } else {
                    send(player, "invalid-speed-mode")
                }
            } else {
                send(player, "fly-usage")
            }
        }
        return false
    }
}