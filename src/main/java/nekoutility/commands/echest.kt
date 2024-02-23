package nekoutility.commands

import nekoutility.Main
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
            Main.send(p, "no-permission")
            return true
        }
        if (args.size > 0 && p.hasPermission("NekoUtility.adm.ec")) {
            val targetPlayer = Bukkit.getPlayer(args[0])
            return if (targetPlayer != null) {
                p.openInventory(targetPlayer.enderChest)
                true
            } else {
                Main.send(p, "player-error")
                true
            }
        }
        p.openInventory(p.enderChest)
        return true
    }
}
