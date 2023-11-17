package NekoEssentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static NekoEssentials.Main.c;
import static NekoEssentials.Main.config;

public class fly implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!label.equalsIgnoreCase("fly") || !(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("NekoEssentials.fly")) {
            player.sendMessage(c(config.getConfig().getString("no-permission")));
            return true;
        }

        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
            player.sendMessage(c(config.getConfig().getString("fly-off")));
        } else {
            player.setAllowFlight(true);
            player.sendMessage(c(config.getConfig().getString("fly-on")));
        }

        return true;
    }
}