package nekoutility

import nekoutility.commands.*
import nekoutility.events.GrowFarmEv
import nekoutility.events.JoinEv
import nekoutility.files.ConfigFile
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        plugin = this
        configcu = ConfigFile(this, "messages")
        prefix = configcu.getConfig().getString("preffix")


        Bukkit.getConsoleSender().sendMessage(c("""
    &f[Neko&5Utility&f] &aStarted successfully.
    &F╔╗╔╔═╗╦╔═╔═╗&5╦ ╦╔╦╗╦╦  ╦╔╦╗╦ ╦
    &F║║║║╣ ╠╩╗║ ║&5║ ║ ║ ║║  ║ ║ ╚╦╝
    &F╝╚╝╚═╝╩ ╩╚═╝&5╚═╝ ╩ ╩╩═╝╩ ╩  ╩ 
    &2v1.3.0 by LukeTheNeko
    &2https://github.com/LukeTheNeko/NekoUtility
    
    """.trimIndent()))
        regCommands()
        regEvents()
    }

    private fun regCommands() {
        getCommand("luz").executor = luz()
        getCommand("fly").executor = fly()
        getCommand("hat").executor = hat()
        getCommand("gm").executor = gm()
        getCommand("echest").executor = echest()
        getCommand("vanish").executor = vanish()
        getCommand("heal").executor = heal()
        getCommand("rename").executor = rename()
        getCommand("relore").executor = relore()
        getCommand("enchant").executor = enchant()
        getCommand("tp").executor = tp()
        getCommand("invsee").executor = invsee()
        getCommand("clear").executor = clear()
        getCommand("speed").executor = speed()
    }

    private fun regEvents() {
        Bukkit.getPluginManager().registerEvents(GrowFarmEv(), this)
        Bukkit.getPluginManager().registerEvents(JoinEv(), this)
    }

    companion object {
        lateinit var plugin: Main
        lateinit var configcu: ConfigFile
        fun send(p: Player, path: String) {
            p.sendMessage(c(prefix + configcu.getConfig().getString(path) ))
        }
        fun send(p: Player, path: String, arg0: String ) {
            p.sendMessage(c(prefix + configcu.getConfig().getString(path).replace("{arg0}",arg0) ))
        }
        fun send(p: Player, path: String, arg0: String, arg1: String ) {
            p.sendMessage(c(prefix + configcu.getConfig().getString(path).replace("{arg0}",arg0).replace("{arg1}",arg1) ))
        }
        fun c(msg: String): String {
            return ChatColor.translateAlternateColorCodes('&', msg)
        }
        lateinit var prefix: String
    }
}