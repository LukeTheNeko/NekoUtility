package nekoutility.commands

import nekoutility.Main.Companion.send
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
            send(player, "no-permission")
            return true
        }
        val heldItem = player.inventory.itemInHand
        if (heldItem == null || heldItem.type == Material.AIR) {
            send(player, "hat-no-item")
            return true
        }
        player.inventory.itemInHand = null
        val helmet = player.inventory.helmet
        player.inventory.helmet = heldItem
        if (helmet != null) {
            player.inventory.addItem(helmet)
        }
        send(player, "hat-success")
        return true
    }
}