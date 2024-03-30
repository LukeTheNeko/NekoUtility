package nekoutility.commands

import nekoutility.Main.Companion.c
import nekoutility.Main.Companion.send
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class relore : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player) {
            val player = sender
            if (player.hasPermission("nekoutility.relore")) {
                if (args.size >= 1) {
                    val item = player.itemInHand
                    val meta = item.itemMeta

                    meta.lore = c(args.joinToString(prefix = "", postfix = "", separator = " ")).split("\\").toMutableList()

                    item.setItemMeta(meta)

                    send(player,"relore-success")

                } else send(player, "relore-usage")
            } else {
                send(player,"no-permission")
            }
        }
        return false
    }
}