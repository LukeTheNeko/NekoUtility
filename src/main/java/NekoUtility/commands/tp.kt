package NekoUtility.commands

import NekoUtility.Main
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import NekoUtility.Main.Companion.c
import java.util.*

class tp : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        val p = sender as Player
        if (p.hasPermission("nekoutility.admin")) {
            when (args!!.size) {
                1 -> {
                    if (Bukkit.getPlayer(args[0]) != null) {
                        p.teleport(Bukkit.getPlayer(args[0]))
                        p.sendMessage(c("&eVocê foi teleportado para &a${args[0]}&e."))
                        if (Bukkit.getPlayer(args[0]).hasPermission("nekoutility.admin"))
                            Bukkit.getPlayer(args[0]).sendMessage(c("&eO player &a${p.name}&e foi teleportado até você."))

                    } else p.sendMessage(c("&cEste player não existe ou está offline."))
                }

                2 -> {
                    if (Bukkit.getPlayer(args[0]) != null && Bukkit.getPlayer(args[1]) != null) {
                        Bukkit.getPlayer(args[0]).teleport(Bukkit.getPlayer(args[1]))
                        if (Bukkit.getPlayer(args[0]).hasPermission("nekoutility.admin"))
                            Bukkit.getPlayer(args[0]).sendMessage(c("&eVocê foi teleportado para &a${args[1]}&e."))
                        if (Bukkit.getPlayer(args[1]).hasPermission("nekoutility.admin"))
                            Bukkit.getPlayer(args[1]).sendMessage(c("&eO player &a${args[0]}&e foi teleportado até você."))
                        p.sendMessage(c("&eVocê teleportou o player &a${args[0]}&e para o player &a${args[1]}&e."))
                    } else {
                        try {
                            p.teleport(Location(p.world, args[0].toDouble(), p.location.y, args[1].toDouble()))
                        } catch (e: IllegalFormatConversionException) {
                            p.sendMessage(c("&cAlgum dos players não existe ou está offline."))
                        }
                    }
                }

                3 -> {
                    try {
                        p.teleport(Location(p.world, args[0].toDouble(), args[1].toDouble(), args[2].toDouble()))
                    } catch (e: IllegalFormatConversionException) {
                        p.sendMessage(c("&cA localização está inválida!"))
                    }
                }

                else -> helpTp(p)
            }
        } else p.sendMessage("&cSem permissão.")
        return true;
    }

    private fun helpTp(p: Player) {
        p.sendMessage(c("&f-=-=-=-&b= &5Rede &fNotz &b=&f-=-=-=-"))
        p.sendMessage(c("&f/&etp &f<&ePlayer&f> &7- Teleporte-se a um player."))
        p.sendMessage(c("&f/&etp &f<&ePlayer&f> (&ePlayer&f) &7- Teleporte um player à outro."))
        p.sendMessage(c("&f/&etp &f<&eX&f> <&eZ&f> &7- Teleporte-se a um coordenada com a mesma altura."))
        p.sendMessage(c("&f/&etp &f<&eX&f> <&eY&f> <&eZ&f> &7- Teleporte-se a uma coordenada exata."))
    }
}