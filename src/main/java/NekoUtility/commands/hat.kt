package NekoUtility.commands

import NekoUtility.Main
import NekoUtility.Main.Companion.c
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class hat : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender !is Player) {
            return false
        }
        val player = sender
        if (!player.hasPermission("nekoutility.hat")) {
            player.sendMessage(c(Main.config!!.getConfig().getString("no-permission")))
            return true
        }
        val heldItem = player.inventory.itemInHand
        if (heldItem == null || heldItem.type == Material.AIR) {
            player.sendMessage(c(Main.config!!.getConfig().getString("hat-no-item")))
            return true
        }
        player.inventory.itemInHand = null
        val helmet = player.inventory.helmet
        player.inventory.helmet = heldItem
        if (helmet != null) {
            player.inventory.addItem(helmet)
        }
        player.sendMessage(c(Main.config!!.getConfig().getString("hat-success")))
        return true
    }
}