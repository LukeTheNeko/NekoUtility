package NekoUtility.commands

import NekoUtility.Main
import NekoUtility.Main.Companion.c
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class fly : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender !is Player) {
            return false
        }
        val p = sender
        if (!p.hasPermission("nekoutility.fly")) {
            p.sendMessage(c(Main.config!!.getConfig().getString("no-permission")))
            return true
        }
        if (p.allowFlight) {
            p.allowFlight = false
            p.sendMessage(c(Main.config!!.getConfig().getString("fly-off")))
        } else {
            p.allowFlight = true
            p.sendMessage(c(Main.config!!.getConfig().getString("fly-on")))
        }
        return true
    }
}