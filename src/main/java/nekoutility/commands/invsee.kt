package nekoutility.commands

import nekoutility.Main.Companion.send
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class invsee : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player) {
            val p = sender
            if (args.isNotEmpty()) {
                if (p.hasPermission("nekoutility.invsee")) {
                    val targetPlayer = Bukkit.getPlayer(args[0])
                    if (targetPlayer != null) {
                        p.openInventory(targetPlayer.inventory)
                        return true
                    } else {
                        send(p, "player-error")
                        return true
                    }
                } else {
                    send(p, "no-permission")
                }
            } else {
                send(p, "missing-arguments")
            }
        }
        return false
    }
}