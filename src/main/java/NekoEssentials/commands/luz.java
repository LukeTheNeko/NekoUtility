package NekoEssentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static NekoEssentials.Main.c;
import static NekoEssentials.Main.config;

public class luz implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("luz")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("NekoEssentials.luz")) {
                    if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                        player.sendMessage(c(config.getConfig().getString("light-off")));
                        return true;
                    }
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0, false, false));
                    player.sendMessage(c(config.getConfig().getString("light-on")));
                    return true;
                } else {
                    player.sendMessage(c(config.getConfig().getString("no-permission")));
                }
            }
        }
        return false;
    }
}