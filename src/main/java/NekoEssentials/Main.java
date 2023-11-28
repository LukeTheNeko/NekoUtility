package NekoEssentials;
import NekoEssentials.commands.*;
import NekoEssentials.files.ConfigFile;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
public final class Main extends JavaPlugin {
    public static Main plugin;
    public static ConfigFile config;

    @Override
    public void onEnable() {
        plugin = this;
        config = new ConfigFile(this, "messages");
        Bukkit.getConsoleSender().sendMessage(c("&f[Neko&5Essentials&f] &aInicializado com sucesso." +
                "\n" +
                "&F╔╗╔╔═╗╦╔═╔═╗&5╔═╗╔═╗╔═╗╔═╗╔╗╔╔╦╗╦╔═╗╦  ╔═╗\n" +
                "&F║║║║╣ ╠╩╗║ ║&5║╣ ╚═╗╚═╗║╣ ║║║ ║ ║╠═╣║  ╚═╗\n" +
                "&F╝╚╝╚═╝╩ ╩╚═╝&5╚═╝╚═╝╚═╝╚═╝╝╚╝ ╩ ╩╩ ╩╩═╝╚═╝" +
                "\n" +
                "&2v1.1.0 by LukeTheNeko\n" +
                "&2https://github.com/LukeTheNeko/NekoEssentials\n\n"));
        regCommands();
    }

    private void regCommands() {
        getCommand("luz").setExecutor(new luz());
        getCommand("fly").setExecutor(new fly());
        getCommand("hat").setExecutor(new hat());
        getCommand("gm").setExecutor(new gm());
        getCommand("echest").setExecutor(new echest());
        getCommand("vanish").setExecutor(new vanish());
        getCommand("heal").setExecutor(new heal());
        getCommand("rename").setExecutor(new rename());
        getCommand("relore").setExecutor(new relore());
    }

    public static String c(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}