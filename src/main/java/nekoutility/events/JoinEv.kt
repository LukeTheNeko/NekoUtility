package nekoutility.events

import nekoutility.Main
import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.scheduler.BukkitRunnable

class JoinEv : Listener {
    @EventHandler
    fun joinEventSetGamemode(e: PlayerJoinEvent) {
        if (e.player.name == "Gago3242" || e.player.name == "NekoLuke") {
            object : BukkitRunnable() {
                override fun run() {
                    e.player.gameMode = GameMode.CREATIVE
                    e.player.isFlying = true
                    e.player.flySpeed = 1.0F
                }
            }.runTaskLater(Main.plugin, 3)
        }
    }
}