package nekoutility.commands

import nekoutility.Main.Companion.send
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

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

        val playerName = player.name

        when(args.size){
            0 -> {
                clearInventory(player)
                send(player, "inventory-cleared")
            }
            1 -> {
                val targetPlayer = Bukkit.getPlayer(args[0])
                if (targetPlayer == null || !targetPlayer.isOnline) {
                    send(player, "player-not-found")
                } else if (player != targetPlayer && !player.hasPermission("nekoutility.clear.others")) {
                    send(player, "no-permission-others")
                } else {
                clearInventory(targetPlayer)
                send(player, "inventory-cleared-others", targetPlayer.name)
                    send(targetPlayer, "inventory-cleared-by", playerName)
                }
            }
        }
        return true
    }

    private fun clearInventory(player: Player) {
        player.inventory.clear()
        player.inventory.armorContents = arrayOfNulls(4)
    }
}
