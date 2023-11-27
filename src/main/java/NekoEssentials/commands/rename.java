package NekoEssentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static NekoEssentials.Main.c;

public class rename implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return false;

        Player p = (Player) sender;

        if (args.length == 0)
            p.sendMessage(c("&eUtilize: &f/&erename &f<&ename&f> &f(&elore&f)"));
        else if (p.getItemInHand() == null)
                p.sendMessage(c("&cSegure um item válido!"));
        else {
            String name = args[0];

            p.setItemInHand(buildItem(p.getItemInHand(), name, Arrays.stream(args).skip(1).collect(Collectors.toList())));
            p.sendMessage(String.valueOf(Arrays.stream(args).skip(1).collect(Collectors.toList()).size()));
        }

        return true;
    }

    private ItemStack buildItem(ItemStack item, String name, List<String> lore) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(c(name + "&r"));
        if (lore.size() != 1)
            lore.replaceAll(s -> c(s + "&r"));
        else lore = Collections.singletonList(c(lore.get(0) + "&r"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
