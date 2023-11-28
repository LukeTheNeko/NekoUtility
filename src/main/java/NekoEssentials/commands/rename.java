package NekoEssentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static NekoEssentials.Main.c;
import static NekoEssentials.Main.config;

public class rename implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("rename")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("NekoEssentials.rename")) {
                    if (args.length >= 1) {
                        ItemStack itemInHand = player.getInventory().getItemInHand();
                        ItemMeta itemMeta = itemInHand.getItemMeta();

                        StringBuilder newName = new StringBuilder();
                        for (String word : args) {
                            newName.append(word).append(" ");
                        }

                        newName = new StringBuilder(ChatColor.translateAlternateColorCodes('&', newName.toString().trim()));

                        itemMeta.setDisplayName(newName.toString());
                        itemInHand.setItemMeta(itemMeta);

                        player.sendMessage(c(config.getConfig().getString("rename-success")));
                    } else {
                        player.sendMessage(c(config.getConfig().getString("rename-usage")));
                    }
                } else {
                    player.sendMessage(c(config.getConfig().getString("no-permission")));
                }
            }
        }
        return false;
    }
}