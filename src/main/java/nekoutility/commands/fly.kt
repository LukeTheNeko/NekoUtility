package nekoutility.commands

import nekoutility.Main.Companion.send
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class fly : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender !is Player) {
            return false
        }
        val p = sender
        if (!p.hasPermission("nekoutility.fly")) {
            send(p, "no-permission")
            return true
        }
        if (p.allowFlight) {
            p.allowFlight = false
            send(p,"fly-off")
        } else {
            p.allowFlight = true
            send(p,"fly-on")
        }
        return true
    }
}