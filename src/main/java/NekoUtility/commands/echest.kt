package NekoUtility.commands

import NekoUtility.Main
import NekoUtility.Main.Companion.c
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class echest : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender !is Player) {
            return false
        }
        val p = sender
        if (!(p.hasPermission("nekoutility.ec") || p.hasPermission("NekoUtility.adm.ec"))) {
            p.sendMessage(c(Main.config!!.getConfig().getString("no-permission")))
            return true
        }
        if (args.size > 0 && p.hasPermission("NekoUtility.adm.ec")) {
            val targetPlayer = Bukkit.getPlayer(args[0])
            return if (targetPlayer != null) {
                p.openInventory(targetPlayer.enderChest)
                true
            } else {
                p.sendMessage(c(Main.config!!.getConfig().getString("player-error")))
                true
            }
        }
        p.openInventory(p.enderChest)
        return true
    }
}
