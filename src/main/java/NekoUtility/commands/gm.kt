package NekoUtility.commands

import NekoUtility.Main
import NekoUtility.Main.Companion.c
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
            player.sendMessage(c(Main.config!!.getConfig().getString("no-permission")))
            return false
        }
        if (args.size == 0) {
            player.sendMessage(c(Main.config!!.getConfig().getString("usecommand")))
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
                player.sendMessage(c(Main.config!!.getConfig().getString("invalidmode")))
                return false
            }
        }
        player.gameMode = gameMode
        val mensagem = Main.config!!.getConfig().getString("gamemode-change").replace("%mode%", gameMode.toString())
        player.sendMessage(c(mensagem))
        return true
    }
}