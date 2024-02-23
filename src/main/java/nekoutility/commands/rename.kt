package nekoutility.commands

import nekoutility.Main.Companion.send
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class rename : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player) {
            val player = sender
            if (player.hasPermission("nekoutility.rename")) {
                if (args.size >= 1) {
                    val itemInHand = player.inventory.itemInHand
                    val itemMeta = itemInHand.itemMeta
                    var newName = StringBuilder()
                    for (word in args) {
                        newName.append(word).append(" ")
                    }
                    newName = StringBuilder(ChatColor.translateAlternateColorCodes('&', newName.toString().trim { it <= ' ' }))
                    itemMeta.displayName = newName.toString()
                    itemInHand.setItemMeta(itemMeta)
                    send(player,"rename-success")
                } else {
                    send(player,"rename-usage")
                }
            } else {
                send(player,"no-permission")
            }
        }

        return false
    }
}