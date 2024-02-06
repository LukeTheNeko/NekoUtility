package NekoUtility

import NekoUtility.commands.*
import NekoUtility.events.GrowFarmEv
import NekoUtility.events.JoinEv
import NekoUtility.files.ConfigFile
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        plugin = this
        Companion.config = ConfigFile(this, "messages")
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
    }

    private fun regEvents() {
        Bukkit.getPluginManager().registerEvents(GrowFarmEv(), this)
        Bukkit.getPluginManager().registerEvents(JoinEv(), this)
    }

    companion object {
        @JvmField
        var plugin: Main? = null
        @JvmField
        var config: ConfigFile? = null
        @JvmStatic
        fun c(msg: String?): String {
            return ChatColor.translateAlternateColorCodes('&', msg)
        }
    }
}