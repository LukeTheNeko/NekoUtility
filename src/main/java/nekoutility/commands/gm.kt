package nekoutility.commands

import nekoutility.Main.Companion.send
import org.bukkit.GameMode
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class gm : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender !is Player) {
            return false
        }
        val player = sender
        if (!player.hasPermission("nekoutility.admin")) {
            send(player, "no-permission")
            return false
        }
        if (args.size == 0) {
            send(player, "use-command")
            return false
        }
        val modeArg = args[0]
        val gameMode: GameMode
        gameMode = when (modeArg) {
            "0" -> GameMode.SURVIVAL
            "1" -> GameMode.CREATIVE
            "2" -> GameMode.ADVENTURE
            "3" -> GameMode.SPECTATOR
            else -> {
                send(player, "invalid-mode")
                return false
            }
        }
        player.gameMode = gameMode
        send(player, "gamemode-change", gameMode.toString())
        return true
    }
}