package nekoutility.commands

import nekoutility.Main.Companion.send
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class clear : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender !is Player) {
            return false
        }

        val player = sender as Player

        if (!player.hasPermission("nekoutility.clear")) {
            send(player, "no-permission")
            return true
        }

        clearInventory(player)
        send(player, "inventory-cleared")
        return true
    }

    private fun clearInventory(player: Player) {
        player.inventory.clear()
        player.inventory.armorContents = arrayOfNulls<ItemStack>(4)
    }
}
