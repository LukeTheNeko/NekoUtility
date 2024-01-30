package NekoEssentials.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;

import static NekoEssentials.Main.plugin;

public class GrowFarmEv implements Listener {
    @EventHandler
    public static void blockGrowEvent(BlockGrowEvent e) {
        if (plugin.getServer().getPluginManager().getPlugin("NotzStorage") == null || !plugin.getServer().getPluginManager().getPlugin("NotzStorage").isEnabled()) {
            switch (e.getNewState().getType()) {
                case NETHER_WARTS:
                    if (e.getNewState().getRawData() == 4)
                        return;
                case CARROT:
                case POTATO:
                case CROPS:
                    if (e.getNewState().getRawData() == 7)
                        return;
                case MELON_STEM:
                case PUMPKIN_STEM:
                    e.setCancelled(true);
            }
        }
    }
}

