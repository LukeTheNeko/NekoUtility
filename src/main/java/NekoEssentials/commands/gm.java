package NekoEssentials.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static NekoEssentials.Main.c;
import static NekoEssentials.Main.config;

public class gm implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("gm")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Esse comando só pode ser executado por jogadores.");
                return false;
            }

            Player player = (Player) sender;
            if (!player.hasPermission("NekoEssentials.admin")) {
                player.sendMessage(c(config.getConfig().getString("no-permission")));
                return false;
            }

            if (args.length == 0) {
                player.sendMessage(c(config.getConfig().getString("usecommand")));
                return false;
            }

            String modeArg = args[0];
            GameMode gameMode;
            switch (modeArg) {
                case "0":
                    gameMode = GameMode.SURVIVAL;
                    break;
                case "1":
                    gameMode = GameMode.CREATIVE;
                    break;
                case "2":
                    gameMode = GameMode.ADVENTURE;
                    break;
                case "3":
                    gameMode = GameMode.SPECTATOR;
                    break;
                default:
                    player.sendMessage(c(config.getConfig().getString("invalidmode")));
                    return false;
            }

            player.setGameMode(gameMode);
            String mensagem = config.getConfig().getString("gamemode-change").replace("%mode%", gameMode.toString());
            player.sendMessage(c(mensagem));
            return true;
        }
        return false;
    }
}