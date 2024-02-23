package nekoutility.commands

import nekoutility.Main.Companion.send
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class heal : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player) {
            val player = sender
            if (player.hasPermission("nekoutility.heal")) {
                player.health = player.maxHealth
                player.foodLevel = 20
                send(player,"healed")
                return true
            } else {
                send(player,"no-permission")
            }
        }
        return false
    }
}