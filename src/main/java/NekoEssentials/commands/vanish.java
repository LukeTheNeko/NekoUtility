package NekoEssentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static NekoEssentials.Main.c;
import static NekoEssentials.Main.config;

public class vanish implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("vanish")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("NekoEssentials.vanish")) {
                    if (player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                        player.removePotionEffect(PotionEffectType.INVISIBILITY);
                        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                            onlinePlayer.showPlayer(player);
                        }
                        player.sendMessage(c(config.getConfig().getString("vanish-off")));
                    } else {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false));
                        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                            onlinePlayer.hidePlayer(player);
                        }
                        player.sendMessage(c(config.getConfig().getString("vanish-on")));
                    }
                    return true;
                } else {
                    player.sendMessage(c(config.getConfig().getString("no-permission")));
                }
            }
        }
        return false;
    }
}