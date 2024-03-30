package nekoutility.commands

import nekoutility.Main.Companion.send
import org.bukkit.Bukkit
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

        val modeArg = args.firstOrNull { it.matches("\\d".toRegex()) } ?: return false
        val targetPlayerName = args.firstOrNull { !it.matches("\\d".toRegex()) } ?: player.name
        val targetPlayer = Bukkit.getPlayer(targetPlayerName)

        if (targetPlayer == null || !targetPlayer.isOnline) {
            send(player, "player-not-found", targetPlayerName)
            return false
        }

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
        targetPlayer.gameMode = gameMode
        if (targetPlayer == player) {
            send(player, "gamemode-change", gameMode.toString())
        } else {
            send(player, "gamemode-change-other", targetPlayerName, gameMode.toString())
            send(targetPlayer, "gamemode-change", gameMode.toString())
        }
        return true
    }
}