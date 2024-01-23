package NekoEssentials.events;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import static NekoEssentials.Main.plugin;

public class JoinEv implements Listener {
    @EventHandler
    public static void joinEventSetGamemode(PlayerJoinEvent e) {
        if (e.getPlayer().getName().equals("Gago3242") || e.getPlayer().getName().equals("NekoLuke")) {
            new BukkitRunnable(){public void run() {
                e.getPlayer().setGameMode(GameMode.CREATIVE);
                e.getPlayer().setFlying(true);
                e.getPlayer().setFlySpeed(1);
            }}.runTaskLater(plugin, 2);
        }
    }

}