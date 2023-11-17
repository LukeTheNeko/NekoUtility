package NekoEssentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;

import static NekoEssentials.Main.c;
import static NekoEssentials.Main.config;

public class hat implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!label.equalsIgnoreCase("hat")) {
            return false;
        }

        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("NekoEssentials.hat")) {
            player.sendMessage(c(config.getConfig().getString("no-permission")));
            return true;
        }

        ItemStack heldItem = player.getInventory().getItemInHand();
        if (heldItem == null || heldItem.getType() == Material.AIR) {
            player.sendMessage(c(config.getConfig().getString("hat-no-item")));
            return true;
        }

        player.getInventory().setItemInHand(null);

        ItemStack helmet = player.getInventory().getHelmet();
        player.getInventory().setHelmet(heldItem);

        if (helmet != null) {
            player.getInventory().addItem(helmet);
        }

        player.sendMessage(c(config.getConfig().getString("hat-success")));
        return true;
    }
}