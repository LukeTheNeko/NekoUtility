package NekoEssentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static NekoEssentials.Main.c;
import static NekoEssentials.Main.config;

public class echest implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(label.equalsIgnoreCase("enderchest") || label.equalsIgnoreCase("ec") || label.equalsIgnoreCase("echest"))) {
            return false;
        }

        if (!(sender instanceof Player)) {
            return false;
        }

        Player p = (Player) sender;

        if (!(p.hasPermission("NekoEssentials.ec") || p.hasPermission("NekoEssentials.adm.ec"))) {
            p.sendMessage(c(config.getConfig().getString("no-permission")));
            return true;
        }

        if (args.length > 0 && p.hasPermission("NekoEssentials.adm.ec")) {
            Player targetPlayer = Bukkit.getPlayer(args[0]);
            if (targetPlayer != null) {
                p.openInventory(targetPlayer.getEnderChest());
                return true;
            } else {
                p.sendMessage(c("&cO jogador não está online ou não existe."));
                return true;
            }
        }

        p.openInventory(p.getEnderChest());
        return true;
    }
}
