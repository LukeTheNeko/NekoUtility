package NekoUtility.commands

import NekoUtility.Main
import NekoUtility.Main.Companion.c
import org.bukkit.ChatColor
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
                    val itemInHand = player.itemInHand
                    val itemMeta = itemInHand.itemMeta
                    val loreLines: MutableList<String> = ArrayList()
                    for (loreLine in args) {
                        loreLines.add(ChatColor.translateAlternateColorCodes('&', loreLine))
                    }
                    if (itemMeta.hasLore()) {
                        itemMeta.lore.clear()
                        itemMeta.lore.addAll(loreLines)
                    } else {
                        itemMeta.lore = loreLines
                    }
                    itemInHand.setItemMeta(itemMeta)
                    player.sendMessage(c(Main.config!!.getConfig().getString("relore-success")))
                } else {
                    player.sendMessage(c(Main.config!!.getConfig().getString("relore-usage")))
                }
            } else {
                player.sendMessage(c(Main.config!!.getConfig().getString("no-permission")))
            }
        }
        return false
    }
}