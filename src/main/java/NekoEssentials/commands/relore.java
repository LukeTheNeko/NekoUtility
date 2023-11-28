package NekoEssentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static NekoEssentials.Main.c;
import static NekoEssentials.Main.config;

public class relore implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("relore")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("NekoEssentials.relore")) {
                    if (args.length >= 1) {
                        ItemStack itemInHand = player.getItemInHand();
                        ItemMeta itemMeta = itemInHand.getItemMeta();

                        List<String> loreLines = new ArrayList<>();
                        for (String loreLine : args) {
                            loreLines.add(ChatColor.translateAlternateColorCodes('&', loreLine));
                        }

                        // Se o item já tiver lore, adiciona a nova lore a ela
                        if (itemMeta.hasLore()) {
                            itemMeta.getLore().clear();
                            itemMeta.getLore().addAll(loreLines);
                        } else {
                            // Se o item não tiver lore, define a nova lore
                            itemMeta.setLore(loreLines);
                        }

                        itemInHand.setItemMeta(itemMeta);

                        player.sendMessage(c(config.getConfig().getString("relore-success")));
                    } else {
                        player.sendMessage(c(config.getConfig().getString("relore-usage")));
                    }
                } else {
                    player.sendMessage(c(config.getConfig().getString("no-permission")));
                }
            }
        }
        return false;
    }
}
