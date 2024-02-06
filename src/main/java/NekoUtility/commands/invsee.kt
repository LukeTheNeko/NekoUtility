package NekoUtility.commands

import NekoUtility.Main
import NekoUtility.Main.Companion.c
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class invsee : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender !is Player) {
            return false
        }
        val p = sender
        if (args.size > 0 && p.hasPermission("nekoutility.invsee")) {
            val targetPlayer = Bukkit.getPlayer(args[0])
            return if (targetPlayer != null) {
                p.openInventory(targetPlayer.inventory)
                true
            } else {
                p.sendMessage(c(Main.config!!.getConfig().getString("player-error")))
                true
            }
        }
        return true
    }
}
