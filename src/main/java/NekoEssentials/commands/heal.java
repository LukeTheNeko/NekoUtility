package NekoEssentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static NekoEssentials.Main.c;
import static NekoEssentials.Main.config;

public class heal implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("heal")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("NekoEssentials.heal")) {
                    player.setHealth(player.getMaxHealth());
                    player.setFoodLevel(20);

                    player.sendMessage(c(config.getConfig().getString("healed")));
                    return true;
                } else {
                    player.sendMessage(c(config.getConfig().getString("no-permission")));
                }
            }
        }
        return false;
    }
}