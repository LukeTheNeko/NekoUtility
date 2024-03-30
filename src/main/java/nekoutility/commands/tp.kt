package nekoutility.commands

import nekoutility.Main.Companion.c
import nekoutility.Main.Companion.send
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class tp : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        val p = sender as Player
        if (p.hasPermission("nekoutility.admin")) {
            when (args!!.size) {
                1 -> {
                    if (Bukkit.getPlayer(args[0]) != null) {
                        p.teleport(Bukkit.getPlayer(args[0]))
                        send(p, "tp-for", args[0])
                    } else send(p, "player-error")
                }

                2 -> {
                    if (Bukkit.getPlayer(args[0]) != null && Bukkit.getPlayer(args[1]) != null) {
                        Bukkit.getPlayer(args[0]).teleport(Bukkit.getPlayer(args[1]))
                        if (Bukkit.getPlayer(args[0]).hasPermission("nekoutility.admin"))
                            send(Bukkit.getPlayer(args[0]), "tp-to-player", args[1])
                        if (Bukkit.getPlayer(args[1]).hasPermission("nekoutility.admin"))
                            send(Bukkit.getPlayer(args[1]), "to-me", args[0])
                        send(p, "tp-p-to", args[0], args[1])
                    } else {
                        try {
                            p.teleport(Location(p.world, args[0].toDouble(), p.location.y, args[1].toDouble()))
                        } catch (e: NumberFormatException) {
                            send(p, "player-error")
                        }
                    }
                }

                3 -> {
                    try {
                        p.teleport(Location(p.world, args[0].toDouble(), args[1].toDouble(), args[2].toDouble()))
                    } catch (e: IllegalFormatConversionException) {
                        send(p,"invalid-location")
                    }
                }

                else -> helpTp(p)
            }
        } else send(p, "no-permission")

        return true;
    }

    private fun helpTp(p: Player) {
        p.sendMessage(c("&f-=-=-=-&b= &f&lRede &5&LNotz &b=&f-=-=-=-"))
        p.sendMessage(c("&f/&etp &f<&ePlayer&f> &7- Teleporte-se a um player."))
        p.sendMessage(c("&f/&etp &f<&ePlayer&f> (&ePlayer&f) &7- Teleporte um player à outro."))
        p.sendMessage(c("&f/&etp &f<&eX&f> <&eZ&f> &7- Teleporte-se a um coordenada com a mesma altura."))
        p.sendMessage(c("&f/&etp &f<&eX&f> <&eY&f> <&eZ&f> &7- Teleporte-se a uma coordenada exata."))
    }
}