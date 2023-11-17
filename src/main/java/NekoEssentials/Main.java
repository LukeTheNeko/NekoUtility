package NekoEssentials;
import NekoEssentials.commands.*;
import NekoEssentials.files.ConfigFile;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static Main plugin;
    public static ConfigFile config;

    @Override
    public void onEnable() {
        plugin = this;
        config = new ConfigFile(this, "messages");

        System.out.println("&6NekoEssentials Ligado!");

        getCommand("luz").setExecutor(new luz());
        getCommand("fly").setExecutor(new fly());
        getCommand("hat").setExecutor(new hat());
        getCommand("gm").setExecutor(new gm());
        getCommand("echest").setExecutor(new echest());
        getCommand("vanish").setExecutor(new vanish());
        getCommand("heal").setExecutor(new heal());
    }

    public static String c(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}