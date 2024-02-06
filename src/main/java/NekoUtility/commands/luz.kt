package NekoUtility.commands

import NekoUtility.Main
import NekoUtility.Main.Companion.c
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class luz : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
            if (sender is Player) {
                val player = sender
                if (player.hasPermission("nekoutility.luz")) {
                    if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                        player.removePotionEffect(PotionEffectType.NIGHT_VISION)
                        player.sendMessage(c(Main.config!!.getConfig().getString("light-off")))
                        return true
                    }
                    player.addPotionEffect(PotionEffect(PotionEffectType.NIGHT_VISION, Int.MAX_VALUE, 0, false, false))
                    player.sendMessage(c(Main.config!!.getConfig().getString("light-on")))
                    return true
                } else {
                    player.sendMessage(c(Main.config!!.getConfig().getString("no-permission")))
                }
            }
        return false
    }
}