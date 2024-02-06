package NekoUtility.commands

import NekoUtility.Main
import NekoUtility.Main.Companion.c
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class vanish : CommandExecutor {
    private val vanishedPlayers: MutableSet<Player> = HashSet()
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
            if (sender is Player) {
                val player = sender
                return if (player.hasPermission("nekoutility.vanish")) {
                    if (isVanished(player)) {
                        for (onlinePlayer in Bukkit.getOnlinePlayers()) {
                            onlinePlayer.showPlayer(player)
                        }
                        vanishedPlayers.remove(player)
                        player.sendMessage(c(Main.config!!.getConfig().getString("vanish-off")))
                    } else {
                        for (onlinePlayer in Bukkit.getOnlinePlayers()) {
                            onlinePlayer.hidePlayer(player)
                        }
                        vanishedPlayers.add(player)
                        player.sendMessage(c(Main.config!!.getConfig().getString("vanish-on")))
                    }
                    true
                } else {
                    player.sendMessage(c(Main.config!!.getConfig().getString("no-permission")))
                    true
                }
            }
        return false
    }

    private fun isVanished(player: Player): Boolean {
        return vanishedPlayers.contains(player)
    }
}