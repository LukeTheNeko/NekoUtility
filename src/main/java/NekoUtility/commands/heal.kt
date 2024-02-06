package NekoUtility.commands

import NekoUtility.Main
import NekoUtility.Main.Companion.c
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
                player.sendMessage(c(Main.config!!.getConfig().getString("healed")))
                return true
            } else {
                player.sendMessage(c(Main.config!!.getConfig().getString("no-permission")))
            }
        }
        return false
    }
}